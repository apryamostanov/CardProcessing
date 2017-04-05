package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.annotations.I_fix_variable_scopes
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = false)
@I_fix_variable_scopes

class T_sim_log_transaction extends T_vsms_base_6_util {

    LinkedHashMap<String, String> p_sim_log_transaction_name_value_map = new LinkedHashMap<String, String>()
    Integer p_log_line_number = GC_ZERO

    @I_black_box("error")
    Integer get_log_line_number() {
        return p_log_line_number
    }

    @I_black_box("error")
    void set_log_line_number(Integer i_log_line_number) {
        p_log_line_number = i_log_line_number
    }

    @I_black_box("error")
    void add_field(String i_field_name, String i_field_value) {
        String l_corrected_field = i_field_value
        if (["F007","F012","F013","F014","F015","F016","F017"].contains(i_field_name)){
            l_corrected_field = l_corrected_field.replace('/','')
            l_corrected_field = l_corrected_field.replace(' ','')
            l_corrected_field = l_corrected_field.replace(':','')
            l_corrected_field = l_corrected_field.replace('.','')
        }
        p_sim_log_transaction_name_value_map.put(i_field_name, l_corrected_field.trim())
    }

    @I_black_box("error")
    String get_field(String i_field_name) {
        if (p_sim_log_transaction_name_value_map.containsKey(i_field_name)) {
            return p_sim_log_transaction_name_value_map.get(i_field_name)
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
        return p_sim_log_transaction_name_value_map.get("F000").substring(2)
    }

    @I_black_box("error")
    String get_mti_message_class() {
        return p_sim_log_transaction_name_value_map.get("F000").substring(0, 2)
    }

    @I_black_box("error")
    String get_top_mti() {
        String l_req_resp = GC_EMPTY_STRING
        if (get_field("F000").substring(2, 3) == "0") {
            l_req_resp = "1"
        } else if (get_field("F000").substring(2, 3) == "1") {
            l_req_resp = "1"
        } else if (get_field("F000").substring(2, 3) == "2") {
            l_req_resp = "3"
        } else if (get_field("F000").substring(2, 3) == "3") {
            l_req_resp = "3"
        }
        return get_field("F000").substring(0, 2) + l_req_resp + get_field("F000").substring(3, 4)
    }

    @I_black_box("error")
    String get_match_key() {
        return get_field("F037") + ":" + get_top_mti() + ":" + get_field("F011")
    }

}
