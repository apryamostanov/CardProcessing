package a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box

import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_EMPTY_STRING

class T_vts_log_transaction {

    HashMap<String, String> p_vts_log_transaction_name_value_map = new HashMap<String, String>()

    @I_black_box
    void add_field(String i_field_name, String i_field_value) {
        p_vts_log_transaction_name_value_map.put(i_field_name, i_field_value)
    }

}
