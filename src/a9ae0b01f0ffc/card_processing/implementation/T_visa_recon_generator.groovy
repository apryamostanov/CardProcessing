package a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.exceptions.E_application_exception

import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_EMPTY_STRING
import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_NULL_OBJ_REF
import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_TRUE
import static a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_VSMS_RECON_VERSION

class T_visa_recon_generator {

    @I_black_box
    T_vts_log_transaction merge(T_vts_log_transaction i_vts_log_transaction_one, T_vts_log_transaction i_vts_log_transaction_two) {
        if (i_vts_log_transaction_one.is_merged() || i_vts_log_transaction_two.is_merged()) {
            T_s.l().log_warning(T_s.s().There_are_more_then_two_transaction_with_the_same_match_key, T_s.t(i_vts_log_transaction_one.get_match_key(), T_s.s().get_match_key), T_s.t(i_vts_log_transaction_one.get_field("F7"), T_s.s().F7), T_s.t(i_vts_log_transaction_two.get_field("F7"), T_s.s().F7))
            return i_vts_log_transaction_one
        } else if (i_vts_log_transaction_one.get_field("MTI") == i_vts_log_transaction_two.get_field("MTI")) {
            throw new E_application_exception(T_s.s().Both_transaction_have_same_MTI, i_vts_log_transaction_one.get_field("MTI"))
        }
        if (i_vts_log_transaction_one.get_mti_message_class() != i_vts_log_transaction_two.get_mti_message_class()) {
            throw new E_application_exception(T_s.s().MTI_message_class_mismatch, i_vts_log_transaction_one.get_field("MTI"), i_vts_log_transaction_two.get_field("MTI"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_vts_log_transaction_one.get_field("MTI"))) {
            throw new E_application_exception(T_s.s().Unsupported_MTI, i_vts_log_transaction_one.get_field("MTI"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_vts_log_transaction_two.get_field("MTI"))) {
            throw new E_application_exception(T_s.s().Unsupported_MTI, i_vts_log_transaction_two.get_field("MTI"))
        }
        T_vts_log_transaction l_merged_vts_log_transaction = new T_vts_log_transaction()
        T_vts_log_transaction l_req = GC_NULL_OBJ_REF as T_vts_log_transaction
        T_vts_log_transaction l_resp = GC_NULL_OBJ_REF as T_vts_log_transaction
        if (i_vts_log_transaction_one.is_request() && i_vts_log_transaction_two.is_response()) {
            l_req = i_vts_log_transaction_one
            l_resp = i_vts_log_transaction_two
        } else if (i_vts_log_transaction_two.is_request() && i_vts_log_transaction_one.is_response()) {
            l_resp = i_vts_log_transaction_one
            l_req = i_vts_log_transaction_two
        }
        for (String l_resp_field_name in l_resp.get_vts_log_transaction_name_value_map().keySet()) {
            l_merged_vts_log_transaction.get_vts_log_transaction_name_value_map().put(l_resp_field_name, GC_EMPTY_STRING)
        }
        for (String l_req_field_name in l_req.get_vts_log_transaction_name_value_map().keySet()) {
            l_merged_vts_log_transaction.get_vts_log_transaction_name_value_map().put(l_req_field_name, GC_EMPTY_STRING)
        }
        for (String l_all_field in l_merged_vts_log_transaction.get_vts_log_transaction_name_value_map().keySet()) {
            l_merged_vts_log_transaction.get_vts_log_transaction_name_value_map().put(l_all_field, T_s.nvl(l_req.get_vts_log_transaction_name_value_map().get(l_all_field), l_resp.get_vts_log_transaction_name_value_map().get(l_all_field)) as String)
        }
        l_merged_vts_log_transaction.set_merged(GC_TRUE)
        return l_merged_vts_log_transaction
    }

    @I_black_box
    ArrayList<T_vts_log_transaction> merge_vts_log_transactions(ArrayList<T_vts_log_transaction> i_vts_log_transactions) {
        ArrayList<T_vts_log_transaction> l_merged_vts_log_transactions = new ArrayList<T_vts_log_transaction>()
        HashMap<String, T_vts_log_transaction> l_transactions_by_key = new HashMap<String, T_vts_log_transaction>()
        for (T_vts_log_transaction l_vts_log_transaction in i_vts_log_transactions) {
            if (l_transactions_by_key.containsKey(l_vts_log_transaction.get_match_key())) {
                l_transactions_by_key.put(l_vts_log_transaction.get_match_key(), merge(l_transactions_by_key.get(l_vts_log_transaction.get_match_key()), l_vts_log_transaction))
            } else {
                l_transactions_by_key.put(l_vts_log_transaction.get_match_key(), l_vts_log_transaction)
            }
        }
        return l_merged_vts_log_transactions
    }

    @I_black_box
    void convert_vts_log_to_ctf(String i_vts_log_file_name, String i_ctf_file_name) {
        T_s.l().log_info(T_s.s().Welcome_to_Visa_VIP_Full_Service_Recon_File_Generator)
        T_s.l().log_info(T_s.s().This_tool_converts_VTS_log_file_to_CTF_TC33_V222xx_reports)
        T_s.l().log_info(T_s.s().Version, GC_VSMS_RECON_VERSION)
        T_vts_log_parser l_vts_log_parser = new T_vts_log_parser()
        ArrayList<T_vts_log_transaction> l_vts_log_transactions = l_vts_log_parser.parse_vts_log(i_vts_log_file_name)
        ArrayList<T_vts_log_transaction> l_merged_vts_log_transactions = merge_vts_log_transactions(l_vts_log_transactions)

        for (T_vts_log_transaction l_merged_transaction in l_merged_vts_log_transactions) {
            l_v22xxx_records.add(create_v22xxx_records(l_merged_transaction))
        }
    }

}
