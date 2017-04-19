package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.annotations.I_fix_variable_scopes
import com.a9ae0b01f0ffc.commons.implementation.config.T_common_conf
import groovy.transform.ToString

import static com.a9ae0b01f0ffc.VSMSGEN.conf.T_vsms_conf.GC_VSMS_RECON_VERSION

@ToString(includeNames = true, includeFields = true, includeSuper = false)
@I_fix_variable_scopes
class T_visa_recon_generator extends T_vsms_base_6_util {

    static FileWriter p_file_writer = GC_NULL_OBJ_REF as FileWriter
    Integer p_number_of_tcrs = GC_ZERO
    Integer p_number_of_transactions = GC_ZERO
    Integer p_total_record_count = GC_ZERO
    Integer p_line_number = GC_ONE_CHAR
    static String p_sim_file_type = GC_ZERO

    @I_black_box
    void write_line(String i_line) {
        p_file_writer.write(i_line + System.lineSeparator())
        p_file_writer.flush()
        p_line_number++
        p_number_of_tcrs++
        p_number_of_transactions++
    }

    @I_black_box("error")
    static T_merged_sim_log_transaction merge(T_sim_log_transaction i_sim_log_transaction_one, T_sim_log_transaction i_sim_log_transaction_two) {
        if (i_sim_log_transaction_one.get_mti_message_class() != i_sim_log_transaction_two.get_mti_message_class()) {
            l().log_warning(s.MTI_message_class_mismatch, i_sim_log_transaction_one.get_field("F000"), i_sim_log_transaction_two.get_field("F000"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_sim_log_transaction_one.get_field("F000"))) {
            l().log_warning(s.Unsupported_MTI, i_sim_log_transaction_one.get_match_key(), i_sim_log_transaction_one.get_field("F000"))
        }
        if (!["0100", "0110", "0120", "0130", "0200", "0210", "0220", "0230", "0400", "0410", "0420", "0430"].contains(i_sim_log_transaction_two.get_field("F000"))) {
            l().log_warning(s.Unsupported_MTI, i_sim_log_transaction_two.get_match_key(), i_sim_log_transaction_one.get_field("F000"))
        }
        T_merged_sim_log_transaction l_merged_vts_log_transaction = new T_merged_sim_log_transaction()
        if (i_sim_log_transaction_one.is_request() && i_sim_log_transaction_two.is_response()) {
            l_merged_vts_log_transaction.set_req(i_sim_log_transaction_one)
            l_merged_vts_log_transaction.set_resp(i_sim_log_transaction_two)
        } else if (i_sim_log_transaction_two.is_request() && i_sim_log_transaction_one.is_response()) {
            l_merged_vts_log_transaction.set_req(i_sim_log_transaction_two)
            l_merged_vts_log_transaction.set_resp(i_sim_log_transaction_one)
        }
        return l_merged_vts_log_transaction
    }

    @I_black_box("error")
    static ArrayList<T_merged_sim_log_transaction> merge_sim_log_transactions(ArrayList<T_sim_log_transaction> i_all_unmerged_vts_log_transactions) {
        ArrayList<T_merged_sim_log_transaction> l_merged_vts_log_transactions = new ArrayList<T_merged_sim_log_transaction>()
        HashMap<String, T_sim_log_transaction> l_unmerged_transaction_by_key = new HashMap<String, T_sim_log_transaction>()
        HashMap<String, T_merged_sim_log_transaction> l_merged_transaction_by_key = new HashMap<String, T_merged_sim_log_transaction>()
        for (T_sim_log_transaction l_unmerged_vts_log_transaction in i_all_unmerged_vts_log_transactions) {
            if (l_unmerged_transaction_by_key.containsKey(l_unmerged_vts_log_transaction.get_match_key())) {
                if (l_merged_transaction_by_key.containsKey(l_unmerged_vts_log_transaction.get_match_key())) {
                    l().log_warning(s.Transaction_with_key_RRN_MTI_STAN_Z1_skipped_at_line_Z2_in_Z3_file_because_transaction_with_same_key_already_found_at_lines_Z4_and_Z5, l_unmerged_vts_log_transaction.get_match_key(), l_unmerged_vts_log_transaction.get_log_line_number(), p_sim_file_type, l_merged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()).get_req().get_log_line_number(), l_merged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()).get_resp().get_log_line_number())
                } else {
                    T_merged_sim_log_transaction l_merged_transaction = merge(l_unmerged_transaction_by_key.get(l_unmerged_vts_log_transaction.get_match_key()), l_unmerged_vts_log_transaction)
                    l_merged_transaction_by_key.put(l_unmerged_vts_log_transaction.get_match_key(), l_merged_transaction)
                    l_merged_vts_log_transactions.add(l_merged_transaction)
                }
            } else {
                l_unmerged_transaction_by_key.put(l_unmerged_vts_log_transaction.get_match_key(), l_unmerged_vts_log_transaction)
            }
        }
        for (T_sim_log_transaction l_non_merged_transaction in i_all_unmerged_vts_log_transactions) {
            if (!l_merged_transaction_by_key.containsKey(l_non_merged_transaction.get_match_key())) {
                l().log_warning(s.Z1_with_RRN_Z2_at_line_Z3_does_not_have_a_Z4_in_Z5_file, l_non_merged_transaction.is_request() ? "Request" : "Response", l_non_merged_transaction.get_field("F037"), l_non_merged_transaction.get_log_line_number(), l_non_merged_transaction.is_request() ? "Response" : "Request", p_sim_file_type)
            }
        }
        return l_merged_vts_log_transactions
    }

    @I_black_box("error")
    static void validate_merged_transaction(T_merged_sim_log_transaction i_merged_sim_log_transaction) {
        if (i_merged_sim_log_transaction.get_field("F002") == GC_EMPTY_STRING) {
            l().log_warning(s.Card_number_F002_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
        }
        if (i_merged_sim_log_transaction.get_req().get_mti_message_class() == "02") {
            if (i_merged_sim_log_transaction.get_field("F004") == GC_EMPTY_STRING) {
                l().log_warning(s.F004_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F005") == GC_EMPTY_STRING) {
                l().log_warning(s.F005_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F006") == GC_EMPTY_STRING) {
                l().log_warning(s.F006_is_missing_for_Clearing_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F049") == GC_EMPTY_STRING) {
                l().log_warning(s.F049_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F050") == GC_EMPTY_STRING) {
                l().log_warning(s.F050_is_missing_for_Clearing_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F051") == GC_EMPTY_STRING) {
                l().log_warning(s.F051_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
        } else {
            if (i_merged_sim_log_transaction.get_field("F004") == GC_EMPTY_STRING) {
                l().log_warning(s.F004_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F006") == GC_EMPTY_STRING) {
                l().log_warning(s.F006_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F049") == GC_EMPTY_STRING) {
                l().log_warning(s.F049_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
            if (i_merged_sim_log_transaction.get_field("F051") == GC_EMPTY_STRING) {
                l().log_warning(s.F051_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
            }
        }
        if (i_merged_sim_log_transaction.get_field("F062.02") == GC_EMPTY_STRING) {
            l().log_warning(s.F062_2_Transaction_Id_is_missing_for_transaction_with_RRN_Z1_with_request_at_line_Z2_and_response_at_line_Z3_in_Z4_file, i_merged_sim_log_transaction.get_field("F037"), i_merged_sim_log_transaction.get_req().get_log_line_number(), i_merged_sim_log_transaction.get_resp().get_log_line_number(), p_sim_file_type)
        }
    }

    @I_black_box
    void convert_sim_log_to_ctf(String i_sim_log_file_name, String i_ctf_file_name, String i_source_name) {
        l().log_info(s.Welcome_to_Visa_VIP_Full_Service_Recon_File_Generator)
        l().log_info(s.This_tool_converts_Z1_log_file_to_CTF_TC33_V222xx_reports, i_source_name)
        l().log_info(s.Version_Z1, GC_VSMS_RECON_VERSION)
        p_file_writer = init_file(i_ctf_file_name, (T_common_conf)c())
        ArrayList<T_sim_log_transaction> l_sim_log_transactions


        p_sim_file_type = i_source_name
        if (i_source_name == VSMSGEN_CONVERT_FINSIM){
            T_finsim_log_parser l_finsim_log_parser = new T_finsim_log_parser()
            l_sim_log_transactions = l_finsim_log_parser.parse_log(i_sim_log_file_name)
        } else { /* ELSE VTS*/
            T_vts_log_parser l_vts_log_parser = new T_vts_log_parser()
            l_sim_log_transactions = l_vts_log_parser.parse_log(i_sim_log_file_name)
        }

        ArrayList<T_merged_sim_log_transaction> l_merged_sim_log_transactions = merge_sim_log_transactions(l_sim_log_transactions)
        T_v22xxx_formatter l_v22xxx_formatter = new T_v22xxx_formatter()
        write_line(l_v22xxx_formatter.make_v22000_string(p_line_number))
        for (T_merged_sim_log_transaction l_merged_transaction in l_merged_sim_log_transactions) {
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
