package a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box
import groovy.transform.Canonical

import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_EMPTY_STRING
import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_FALSE

@Canonical
class T_vts_log_transaction {

    LinkedHashMap<String, String> p_vts_log_transaction_name_value_map = new LinkedHashMap<String, String>()
    Boolean p_is_merged = GC_FALSE

    @I_black_box("error")
    void add_field(String i_field_name, String i_field_value) {
        p_vts_log_transaction_name_value_map.put(i_field_name, i_field_value)
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

    LinkedHashMap<String, String> get_vts_log_transaction_name_value_map() {
        return p_vts_log_transaction_name_value_map
    }

    void set_vts_log_transaction_name_value_map(LinkedHashMap<String, String> i_vts_log_transaction_name_value_map) {
        p_vts_log_transaction_name_value_map = i_vts_log_transaction_name_value_map
    }

    Boolean is_merged() {
        return p_is_merged
    }

    void set_merged(Boolean i_is_merged) {
        p_is_merged = i_is_merged
    }
}
