package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.annotations.I_fix_variable_scopes
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = false)
@I_fix_variable_scopes
class T_vts_log_parser  extends T_vsms_base_6_util {

    static final String PC_INSIDE_TRANSACTION_BLOCK = "Inside transaction block"
    static final String PC_OUTSIDE_TRANSACTION_BLOCK = "Outside transaction block"
    static final String PC_HOST_TO_VTS = "Host to VTS"
    static final String PC_VTS_TO_HOST = "VTS to Host"
    static final Integer PC_VTS_LOG_VALUE_POSITION_SENT = 41
    static final Integer PC_VTS_LOG_VALUE_POSITION_RECEIVED = 66
    static Integer p_current_vts_log_file_line_number = GC_ZERO
    String p_current_direction = GC_EMPTY_STRING
    ArrayList<T_sim_log_transaction> p_log_transactions = new ArrayList<T_sim_log_transaction>()
    T_sim_log_transaction p_current_transaction = GC_NULL_OBJ_REF as T_sim_log_transaction
    String p_previous_position = PC_OUTSIDE_TRANSACTION_BLOCK
    String p_current_position = PC_OUTSIDE_TRANSACTION_BLOCK

    @I_black_box("error")
    static Boolean is_block_starting_line(String i_line) {
        return i_line.contains("FLD ID")
    }

    @I_black_box("error")
    Boolean is_block_finishing_line(String i_line) {
        return i_line.contains("--------") && p_current_position == PC_INSIDE_TRANSACTION_BLOCK
    }

    @I_black_box("error")
    static Boolean is_vts_to_host(String i_line) {
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

    @I_black_box("error")
    void initialize_transaction() {
        p_current_transaction = new T_sim_log_transaction()
        p_current_transaction.set_log_line_number(p_current_vts_log_file_line_number)
    }

    @I_black_box("error")
    void finalize_transaction() {
        p_log_transactions.add(p_current_transaction)
    }

    @I_black_box("error")
    static String parse_field_name(String i_line) {
        String l_field_name = i_line.substring(GC_FIRST_CHAR, i_line.indexOf(GC_SPACE))
        /* Change MTI to F000 */
        if (l_field_name == "MTI")
            return "F000"

        /* No changes in header subfields*/
        if (l_field_name.substring(0, 1) == "H")
            return l_field_name

        return normalize_field_naming(l_field_name)
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
        String l_field_name = parse_field_name(i_line)
        String l_field_value = parse_field_value(i_line)

        if (!["{Expected, But Not Received}", "{Received, But Not Expected}"].contains(l_field_value)) {
            p_current_transaction.add_field(l_field_name, l_field_value)
        }
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

    @I_black_box("error")
    ArrayList<T_sim_log_transaction> parse_log(String i_log_file_name) {
        new File(i_log_file_name).eachLine { String l_line ->
            p_current_vts_log_file_line_number ++
            process_line(l_line)
        }
        return p_log_transactions
    }

}
