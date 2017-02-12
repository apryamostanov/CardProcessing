package com.a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.black_box.main.T_u
import com.a9ae0b01f0ffc.card_processing.main.T_app_const

import static com.a9ae0b01f0ffc.card_processing.main.T_app_commons.GC_VSMS_RECON_VERSION

class T_visa_recon_generator {

    static FileWriter p_file_writer = T_app_const.GC_NULL_OBJ_REF as FileWriter
    Integer p_number_of_tcrs = T_app_const.GC_ZERO
    Integer p_number_of_transactions = T_app_const.GC_ZERO
    Integer p_total_record_count = T_app_const.GC_ZERO
    Integer p_line_number = T_app_const.GC_ONE_CHAR

    @I_black_box
    void write_line(String i_line) {
        p_file_writer.write(i_line + System.lineSeparator())
        p_file_writer.flush()
        p_line_number++
        p_number_of_tcrs++
        p_number_of_transactions++
    }

    @I_black_box
    T_merged_vts_log_transaction merge(T_vts_log_transaction i_vts_log_transaction_one, T_vts_log_transaction i_vts_log_transaction_two) {
        if (i_vts_log_transaction_one.get_mti_message_class() != i_vts_log_transaction_two.get_mti_message_class()) {
            T_s.l().log_warning(T_s.s().MTI_message_class_mismatch, i_vts_log_transaction_one.get_field("MTI"), i_vts_log_transaction_two.get_field("MTI"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_vts_log_transaction_one.get_field("MTI"))) {
            T_s.l().log_warning(T_s.s().Unsupported_MTI, i_vts_log_transaction_one.get_match_key(), i_vts_log_transaction_one.get_field("MTI"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_vts_log_transaction_two.get_field("MTI"))) {
            T_s.l().log_warning(T_s.s().Unsupported_MTI, i_vts_log_transaction_two.get_match_key(), i_vts_log_transaction_one.get_field("MTI"))
        }
        T_merged_vts_log_transaction l_merged_vts_log_transaction = new T_merged_vts_log_transaction()
        if (i_vts_log_transaction_one.is_request() && i_vts_log_transaction_two.is_response()) {
            l_merged_vts_log_transaction.set_req(i_vts_log_transaction_one)
            l_merged_vts_log_transaction.set_resp(i_vts_log_transaction_two)
        } else if (i_vts_log_transaction_two.is_request() && i_vts_log_transaction_one.is_response()) {
            l_merged_vts_log_transaction.set_req(i_vts_log_transaction_two)
            l_merged_vts_log_transaction.set_resp(i_vts_log_transaction_one)
        }
        return l_merged_vts_log_transaction
    }

    @I_black_box("error")
    ArrayList<T_merged_vts_log_transaction> merge_vts_log_transactions(ArrayList<T_vts_log_transaction> i_all_unmerged_vts_log_transactions) {
        ArrayList<T_merged_vts_log_transaction> l_merged_vts_log_transactions = new ArrayList<T_merged_vts_log_transaction>()
        HashMap<String, T_vts_log_transaction> l_unmerged_transaction_by_key = new HashMap<String, T_vts_log_transaction>()
        HashMap<String, T_merged_vts_log_transaction> l_merged_transaction_by_key = new HashMap<String, T_merged_vts_log_transaction>()
        for (T_vts_log_transaction l_unmerged_vts_log_transaction in i_all_unmerged_vts_log_transactions) {
            if (l_unmerged_transaction_by_key.containsKey(l_unmerged_vts_log_transaction.get_match_key())) {
                if (l_merged_transaction_by_key.containsKey(l_unmerged_vts_log_transaction.get_match_key())) {
                    T_s.l().log_warning(T_s.s().Transaction_with_key_RRN_MTI_STAN_Z1_skipped_at_VTS_Log_file_line_Z2_because_transaction_with_same_key_already_found_at_lines_Z3_and_Z4, l_unmerged_vts_log_transaction.get_match_key(), l_unmerged_vts_log_transaction.get_vts_log_line_number(), l_merged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()).get_req().get_vts_log_line_number(), l_merged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()).get_resp().get_vts_log_line_number())
                } else {
                    T_merged_vts_log_transaction l_merged_transaction = merge(l_unmerged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()), l_unmerged_vts_log_transaction)
                    l_merged_transaction_by_key.put(l_unmerged_vts_log_transaction.get_match_key(), l_merged_transaction)
                    l_merged_vts_log_transactions.add(l_merged_transaction)
                }
            } else {
                l_unmerged_transaction_by_key.put(l_unmerged_vts_log_transaction.get_match_key(), l_unmerged_vts_log_transaction)
            }
        }
        for (T_vts_log_transaction l_non_merged_transaction in i_all_unmerged_vts_log_transactions) {
            if (!l_merged_transaction_by_key.containsKey(l_non_merged_transaction.get_match_key())) {
                T_s.l().log_warning(T_s.s().Z1_with_RRN_Z2_at_line_Z3_does_not_have_a_Z4_in_VTS_log, l_non_merged_transaction.is_request()?"Request":"Response", l_non_merged_transaction.get_field("F37"), l_non_merged_transaction.get_vts_log_line_number(), l_non_merged_transaction.is_request()?"Response":"Request")
            }
        }
        return l_merged_vts_log_transactions
    }

    @I_black_box
    void validate_merged_transaction(T_merged_vts_log_transaction i_merged_vts_log_transaction) {
        if (i_merged_vts_log_transaction.get_field("F2") == T_app_const.GC_EMPTY_STRING) {
            T_s.l().log_warning(T_s.s().Card_number_F002_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
        }
        if (i_merged_vts_log_transaction.get_req().get_mti_message_class() == "02") {
            if (i_merged_vts_log_transaction.get_field("F4") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F004_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F5") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F005_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F6") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F006_is_missing_for_Clearing_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F49") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F049_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F50") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F050_is_missing_for_Clearing_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F51") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F051_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
        } else {
            if (i_merged_vts_log_transaction.get_field("F4") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F004_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F6") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F006_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F49") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F049_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
            if (i_merged_vts_log_transaction.get_field("F51") == T_app_const.GC_EMPTY_STRING) {
                T_s.l().log_warning(T_s.s().F051_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3, i_merged_vts_log_transaction.get_field("F37"), i_merged_vts_log_transaction.get_req().get_vts_log_line_number(), i_merged_vts_log_transaction.get_resp().get_vts_log_line_number())
            }
        }
    }

    @I_black_box
    void convert_vts_log_to_ctf(String i_vts_log_file_name, String i_ctf_file_name) {
        T_s.l().log_info(T_s.s().Welcome_to_Visa_VIP_Full_Service_Recon_File_Generator)
        T_s.l().log_info(T_s.s().This_tool_converts_VTS_log_file_to_CTF_TC33_V222xx_reports)
        T_s.l().log_info(T_s.s().Version_Z1, GC_VSMS_RECON_VERSION)
        p_file_writer = T_u.init_file(i_ctf_file_name)
        T_vts_log_parser l_vts_log_parser = new T_vts_log_parser()
        ArrayList<T_vts_log_transaction> l_vts_log_transactions = l_vts_log_parser.parse_vts_log(i_vts_log_file_name)
        ArrayList<T_merged_vts_log_transaction> l_merged_vts_log_transactions = merge_vts_log_transactions(l_vts_log_transactions)
        T_v22xxx_formatter l_v22xxx_formatter = new T_v22xxx_formatter()
        write_line(l_v22xxx_formatter.make_v22000_string(p_line_number))
        for (T_merged_vts_log_transaction l_merged_transaction in l_merged_vts_log_transactions) {
            validate_merged_transaction(l_merged_transaction)
            write_line(l_v22xxx_formatter.make_v22200_string(l_merged_transaction, p_line_number))
            write_line(l_v22xxx_formatter.make_v22210_string(l_merged_transaction, p_line_number))
            write_line(l_v22xxx_formatter.make_v22220_string(l_merged_transaction, p_line_number))
            write_line(l_v22xxx_formatter.make_v22230_string(l_merged_transaction, p_line_number))
            write_line(l_v22xxx_formatter.make_v22260_string(l_merged_transaction, p_line_number))
            write_line(l_v22xxx_formatter.make_v22261_string(l_merged_transaction, p_line_number))
            p_total_record_count++
        }
        write_line(l_v22xxx_formatter.make_v22900_string(p_line_number, p_total_record_count))
        write_line(l_v22xxx_formatter.make_tc91_string(p_number_of_tcrs + 1, p_number_of_transactions + 1))
        write_line(l_v22xxx_formatter.make_tc92_string(p_number_of_tcrs + 1, p_number_of_transactions + 1))
    }

}
