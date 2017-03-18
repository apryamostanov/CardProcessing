package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.annotations.I_fix_variable_scopes
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = false)
@I_fix_variable_scopes
class T_vts_log_transaction extends T_vsms_base_6_util{

    LinkedHashMap<String, String> p_vts_log_transaction_name_value_map = new LinkedHashMap<String, String>()
    Integer p_vts_log_line_number = GC_ZERO

    @I_black_box("error")
    Integer get_vts_log_line_number() {
        return p_vts_log_line_number
    }

    @I_black_box("error")
    void set_vts_log_line_number(Integer i_vts_log_line_number) {
        p_vts_log_line_number = i_vts_log_line_number
    }

    @I_black_box("error")
    void add_field(String i_field_name, String i_field_value) {
        if (!["{Expected, But Not Received}", "{Received, But Not Expected}"].contains(i_field_value)) {
            p_vts_log_transaction_name_value_map.put(i_field_name, i_field_value.trim())
        }
    }

    @I_black_box("error")
    String get_field(String i_field_name) {
        if (p_vts_log_transaction_name_value_map.containsKey(i_field_name)) {
            return p_vts_log_transaction_name_value_map.get(i_field_name)
        } else {
            return GC_EMPTY_STRING
        }
    }

    @I_black_box("error")
    Boolean is_request() {
        return get_mti_req_resp() == "00" || get_mti_req_resp() == "20"
    }

    @I_black_box("error")
    Boolean is_response() {
        return get_mti_req_resp() == "10" || get_mti_req_resp() == "30"
    }

    @I_black_box("error")
    String get_mti_req_resp() {
        return p_vts_log_transaction_name_value_map.get("MTI").substring(2)
    }

    @I_black_box("error")
    String get_mti_message_class() {
        return p_vts_log_transaction_name_value_map.get("MTI").substring(0, 2)
    }

    @I_black_box("error")
    String get_top_mti() {
        String l_req_resp = GC_EMPTY_STRING
        if (get_field("MTI").substring(2, 3) == "0") {
            l_req_resp = "1"
        } else if (get_field("MTI").substring(2, 3) == "1") {
            l_req_resp = "1"
        } else if (get_field("MTI").substring(2, 3) == "2") {
            l_req_resp = "3"
        } else if (get_field("MTI").substring(2, 3) == "3") {
            l_req_resp = "3"
        }
        return get_field("MTI").substring(0, 2) + l_req_resp + get_field("MTI").substring(3, 4)
    }

    @I_black_box("error")
    String get_match_key() {
        return get_field("F37") + ":" + get_top_mti() + ":" + get_field("F11")
    }

}
