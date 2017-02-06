package a9ae0b01f0ffc.card_processing.implementation

import a9ae0b01f0ffc.card_processing.main.T_shortcuts
import com.a9ae0b01f0ffc.black_box.main.T_s

import static a9ae0b01f0ffc.card_processing.main.T_app_commons.*
import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box

class T_vts_log_parser {

    static final String PC_INSIDE_TRANSACTION_BLOCK = "Inside transaction block"
    static final String PC_OUTSIDE_TRANSACTION_BLOCK = "Outside transaction block"
    static final String PC_HOST_TO_VTS = "Host to VTS"
    static final String PC_VTS_TO_HOST = "VTS to Host"
    static final Integer PC_VTS_LOG_VALUE_POSITION_SENT = 41
    static final Integer PC_VTS_LOG_VALUE_POSITION_RECEIVED = 66
    String p_current_direction = GC_EMPTY_STRING
    ArrayList<T_vts_log_transaction> p_vts_log_transactions = new ArrayList<T_vts_log_transaction>()
    T_vts_log_transaction p_current_transaction = GC_NULL_OBJ_REF as T_vts_log_transaction
    HashMap<String, T_vts_log_transaction> p_vts_log_transactions_by_matching_key = new HashMap<String, T_vts_log_transaction>()
    String p_previous_position = PC_OUTSIDE_TRANSACTION_BLOCK
    String p_current_position = PC_OUTSIDE_TRANSACTION_BLOCK

    @I_black_box("error")
    Boolean is_block_starting_line(String i_line) {
        return i_line.contains("FLD ID")
    }

    @I_black_box("error")
    Boolean is_block_finishing_line(String i_line) {
        return i_line.contains("--------") && p_current_position == PC_INSIDE_TRANSACTION_BLOCK
    }

    @I_black_box
    Boolean is_vts_to_host(String i_line) {
        return !i_line.contains("EXPECTED VALUE")
    }

    @I_black_box("error")
    void analyze_line(String i_line) {
        p_previous_position = p_current_position
        if (is_block_starting_line(i_line)) {
            p_current_position = PC_INSIDE_TRANSACTION_BLOCK
            if (is_vts_to_host(i_line)) {
                p_current_direction = PC_VTS_TO_HOST
            } else {
                p_current_direction = PC_HOST_TO_VTS
            }
        } else if (is_block_finishing_line(i_line)) {
            p_current_position = PC_OUTSIDE_TRANSACTION_BLOCK
        }
    }

    @I_black_box("error")
    Boolean entered_transaction_block() {
        //T_s.l().log_debug(T_s.s().FLAGS, T_s.r(p_current_position, "p_current_position"), T_s.r(p_previous_position, "p_previous_position"))
        return p_current_position == PC_INSIDE_TRANSACTION_BLOCK && p_previous_position == PC_OUTSIDE_TRANSACTION_BLOCK
    }

    @I_black_box("error")
    Boolean exited_from_transaction_block() {
        return p_current_position == PC_OUTSIDE_TRANSACTION_BLOCK && p_previous_position == PC_INSIDE_TRANSACTION_BLOCK
    }

    @I_black_box("error")
    Boolean inside_transaction_block() {
        return p_current_position == PC_INSIDE_TRANSACTION_BLOCK && p_previous_position == PC_INSIDE_TRANSACTION_BLOCK
    }

    @I_black_box
    void initialize_transaction() {
        p_current_transaction = new T_vts_log_transaction()
    }

    @I_black_box
    void finalize_transaction() {
        p_vts_log_transactions.add(p_current_transaction)
        T_s.l().log_debug(T_s.s().Transaction_created, T_s.t(p_current_transaction, T_s.s().p_current_transaction))
    }

    @I_black_box("error")
    String parse_field_name(String i_line) {
        return i_line.substring(GC_FIRST_CHAR, i_line.indexOf(GC_SPACE))
    }

    @I_black_box("error")
    Integer get_value_position() {
        if (p_current_direction == PC_VTS_TO_HOST) {
            return PC_VTS_LOG_VALUE_POSITION_SENT
        } else {
            return PC_VTS_LOG_VALUE_POSITION_RECEIVED
        }
    }

    @I_black_box("error")
    String parse_field_value(String i_line) {
        if (i_line.length() > PC_VTS_LOG_VALUE_POSITION_SENT) {
            return i_line.substring(get_value_position()).trim()
        } else {
            return GC_EMPTY_STRING
        }
    }

    @I_black_box("error")
    void accrue_fields(String i_line) {
        p_current_transaction.add_field(parse_field_name(i_line), parse_field_value(i_line))
    }

    @I_black_box("error")
    void process_line(String i_line) {
        analyze_line(i_line)
        if (entered_transaction_block()) {
            initialize_transaction()
        } else if (inside_transaction_block()) {
            accrue_fields(i_line)
        } else if (exited_from_transaction_block()) {
            finalize_transaction()
        }
    }

    @I_black_box
    ArrayList<T_vts_log_transaction> parse_vts_log(String i_vts_log_file_name) {
        new File(i_vts_log_file_name).eachLine { String l_line ->
            process_line(l_line)
        }
        return p_vts_log_transactions
    }

}
