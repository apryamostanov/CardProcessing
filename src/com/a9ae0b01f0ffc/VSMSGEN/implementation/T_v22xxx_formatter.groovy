package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.VSMSGEN.main.T_app_const
import com.a9ae0b01f0ffc.VSMSGEN.main.T_u

import java.text.DecimalFormat

class T_v22xxx_formatter {

    @I_black_box("error")
    String make_tc91_string(Integer i_total_number_of_tcrs, Integer i_total_number_of_transactions) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "91" //p_transaction_code
        l_result_string += "0" //p_transaction_code_qualifier
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_transaction_component_sequence_n
        l_result_string += "474646" //p_bin
        l_result_string += T_u.d2s(new Date(), "yy") + T_u.d2s(new Date(), "D").padLeft(3, "0") //d2s
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(15, "0") //p_destination_amount
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(12, "0") //p_number_of_monetary_transactions
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(6, "0") //p_batch_number
        l_result_string += i_total_number_of_tcrs.toString().padLeft(12, "0") //p_number_of_tcrs
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(6, "0")
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(8, " ") //p_center_batch_id
        l_result_string += i_total_number_of_transactions.toString().padLeft(9, "0") //p_number_of_transactions
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(18, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(15, "0") //p_source_amount
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(7, "0") //GC_SPACE
        return l_result_string
    }

    @I_black_box("error")
    String make_tc92_string(Integer i_total_number_of_tcrs, Integer i_total_number_of_transactions) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "92" //p_transaction_code
        l_result_string += "0" //p_transaction_code_qualifier
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_transaction_component_sequence_n
        l_result_string += "474646" //p_bin
        l_result_string += T_u.d2s(new Date(), "yy") + T_u.d2s(new Date(), "D").padLeft(3, "0") //d2s
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(15, "0") //p_destination_amount
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(12, "0") //p_number_of_monetary_transactions
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(6, "0") //p_batch_number
        l_result_string += i_total_number_of_tcrs.toString().padLeft(12, "0") //p_number_of_tcrs
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(6, "0")
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(8, " ") //p_center_batch_id
        l_result_string += i_total_number_of_transactions.toString().padLeft(9, "0") //p_number_of_transactions
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(18, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padLeft(15, "0") //p_source_amount
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.toString().padRight(15, "0") //GC_ZERO_INTEGER
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(7, "0") //GC_SPACE
        return l_result_string
    }

    @I_black_box("error")
    String make_v22900_string(Integer i_line_number, Integer i_total_record_count) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += "474646"
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22900" //p_record_type
        l_result_string += "4746460002".padRight(10, " ") //raw data recepient
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(10, " ")
        l_result_string += i_total_record_count.toString().padLeft(11, "0")
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(93, " ")
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(3, " ")
        l_result_string += "0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22000_string(Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += "474646" //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22000" //p_record_type
        l_result_string += T_u.d2s(new Date(), "yyMMdd")
        l_result_string += "4746460002".padRight(10, " ") //raw data recepient
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(10, " ")
        l_result_string += "VSS".padRight(9, " ")
        l_result_string += T_u.d2s(new Date(), "yyMMdd").padRight(10, " ")
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(82, " ")
        l_result_string += "0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22261_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        String l_adjusted_amount
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22261" //p_record_type
        l_adjusted_amount = T_u.adjust_decdigits_V22260(i_merged_vts_log_transaction.get_field("F5"), i_merged_vts_log_transaction.get_field("F50"))
        if (i_merged_vts_log_transaction.get_field("F5") != T_app_const.GC_EMPTY_STRING) {
            l_result_string += (new DecimalFormat(".000000").format(Integer.valueOf(l_adjusted_amount) * 0.016 / 100)).replace(T_app_const.GC_POINT, T_app_const.GC_EMPTY_STRING).padLeft(11, "0")
            //p_reimbursement_fee
            l_result_string += "C".padRight(1, " ") //p_reimbursement_fee_debit_credit_indicator
        } else {
            l_result_string += T_app_const.GC_EMPTY_STRING.padLeft(11, "0") //p_reimbursement_fee
            l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, " ") //p_reimbursement_fee_debit_credit_indicator
        }
        l_result_string += T_app_const.GC_EMPTY_STRING.padLeft(11, "0") //p_cash_back_irf_amount_settlement_currency
        l_result_string += T_app_const.GC_EMPTY_STRING.padLeft(11, "0") //p_transaction_integrity_fee
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, " ") //p_transaction_integrity_fee_debit_credit_indicator
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(92, " ") //GC_SPACE
        l_result_string += "0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22260_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        String l_adjusted_amount
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22260" //p_record_type
        l_result_string += T_u.s2d(i_merged_vts_log_transaction.get_field("F15"), "MMdd").format("yy").substring(T_app_const.GC_ONE_CHAR) + T_u.s2d(i_merged_vts_log_transaction.get_field("F15"), "MMdd").format("D").padLeft(3, "0")
        //p_rate_table_date
        l_adjusted_amount = T_u.adjust_decdigits_V22260(i_merged_vts_log_transaction.get_field("F4"), i_merged_vts_log_transaction.get_field("F49"))
        l_result_string += T_u.format_cobol_number(l_adjusted_amount).padLeft(12, "0")
        //p_transaction_amount
        l_result_string += i_merged_vts_log_transaction.get_field("F49").padRight(3, " ") //p_transaction_currency_code
        l_adjusted_amount = T_u.adjust_decdigits_V22260(i_merged_vts_log_transaction.get_field("F5"), i_merged_vts_log_transaction.get_field("F50"))
        l_result_string += T_u.format_cobol_number(l_adjusted_amount).padLeft(12, "0")
        //p_settlement_amount
        l_result_string += i_merged_vts_log_transaction.get_field("F50").padRight(3, " ") //p_settlement_currency_code
        l_adjusted_amount = T_u.adjust_decdigits_V22260(i_merged_vts_log_transaction.get_field("F6"), i_merged_vts_log_transaction.get_field("F51"))
        l_result_string += T_u.format_cobol_number(l_adjusted_amount).padLeft(12, "0")
        //p_cardholder_billing_amount
        l_result_string += i_merged_vts_log_transaction.get_field("F51").padRight(3, " ")
        //p_cardholder_billing_currency_code
        l_result_string += T_u.format_cobol_number(i_merged_vts_log_transaction.get_field("F61.2")).padLeft(12, "0")
        //p_cardholder_billing_other_amount
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(18, " ") //p_filler1
        l_result_string += T_u.format_cobol_number("0").padLeft(9, "0")
        //p_optional_issuer_fee_settlement_currency
        l_result_string += T_u.format_cobol_number("0").padLeft(9, "0")
        //p_optional_issuer_fee_cardholder_billing_currency
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(18, " ") //p_filler2
        l_result_string += T_u.format_cobol_number("0").padLeft(9, "0")
        //p_optional_issuer_isa_amount_in_settlement_currency
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(3, " ") //GC_SPACE
        l_result_string += "0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22230_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22230" //p_record_type
        l_result_string += "                  000000 0000000000 000000000000   000000000000                                                                0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22220_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22220" //p_record_type
        l_result_string += i_merged_vts_log_transaction.get_field("F25").padRight(2, " ") //p_pos_condition_code
        l_result_string += i_merged_vts_log_transaction.get_field("F22").padRight(3, " ").substring(0, 3)
        //p_pos_entry_mode
        l_result_string += i_merged_vts_log_transaction.get_field("F60.1").padLeft(2, "0") //p_pos_terminal_type
        l_result_string += i_merged_vts_log_transaction.get_field("F60.2").padRight(1, " ")
        //p_pos_terminal_entry_capability
        l_result_string += i_merged_vts_log_transaction.get_field("F18").padRight(4, " ") //p_merchants_type
        l_result_string += i_merged_vts_log_transaction.get_field("F41").padRight(8, " ") //p_card_acceptor_terminal_id
        l_result_string += i_merged_vts_log_transaction.get_field("F42").padRight(15, " ") //p_card_acceptor_id
        l_result_string += i_merged_vts_log_transaction.get_field("F43.1").padRight(25, " ") //p_card_acceptor_name
        l_result_string += i_merged_vts_log_transaction.get_field("F43.2").padRight(13, " ") //p_card_acceptor_city
        l_result_string += i_merged_vts_log_transaction.get_field("F43.3").padRight(2, " ") //p_card_acceptor_country
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(2, "0") //p_geo_state_code
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(5, " ") //p_geo_zip_code_five
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(4, " ") //p_geo_zip_code_four
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(3, "0") //p_geo_country_code
        l_result_string += i_merged_vts_log_transaction.get_field("F19").padRight(3, "0")
        //p_acquiring_institution_country_code
        l_result_string += i_merged_vts_log_transaction.get_field("F20").padRight(3, "0") //p_pan_extended_country_code
        l_result_string += i_merged_vts_log_transaction.get_field("F33").padRight(11, " ") //p_forwarding_institution_id
        l_result_string += i_merged_vts_log_transaction.get_field("F21").padRight(3, "0")
        //p_forwarding_institution_country_code
        l_result_string += i_merged_vts_log_transaction.get_field("F52") == T_app_const.GC_EMPTY_STRING ? "0" : "1"
        //p_customer_identification_method
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) + "0002"
        //p_issuer_affiliate_bin
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(3, " ")
        l_result_string += i_merged_vts_log_transaction.get_field("F126.13").padRight(3, " ")
        //p_recurring_payment_indicator_flag
        l_result_string += " " //GC_SPACE
        l_result_string += "0" //GC_SMS_LINE_END
        return l_result_string
    }

    @I_black_box("error")
    String make_v22210_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22210" //p_record_type
        l_result_string += i_merged_vts_log_transaction.get_field("F13").padLeft(4, " ") //p_local_transaction_date
        l_result_string += i_merged_vts_log_transaction.get_field("F12").padLeft(6, " ") //p_local_transaction_time
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_giv_flag
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_giv_flag_previous
        l_result_string += i_merged_vts_log_transaction.get_field("F32").padLeft(11, "0") //p_acquiring_institution_id
        l_result_string += i_merged_vts_log_transaction.get_field("F63.8").padRight(8, "0") //p_acquiring_business_id
        l_result_string += i_merged_vts_log_transaction.get_field("H06").padRight(6, " ") //p_source_station_id
        l_result_string += i_merged_vts_log_transaction.get_field("H05").padRight(6, " ") //p_destination_station_id
        l_result_string += i_merged_vts_log_transaction.get_field("F63.3").padRight(4, "0") //p_message_reason_code
        l_result_string += i_merged_vts_log_transaction.get_field("F63.4").padRight(4, "0") //p_stip_reason_code
        l_result_string += i_merged_vts_log_transaction.get_field("F38").padRight(6, " ") //p_authorization_id
        l_result_string += i_merged_vts_log_transaction.get_field("F63.1").padRight(4, "0") //p_network_id
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_advice_source_flag
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_advice_transaction_flag
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, "0") //p_base_1_bill_flag
        l_result_string += "2" //p_track_data_indicator
        l_result_string += i_merged_vts_log_transaction.get_field("F63.11").padRight(1, "0") //p_reimbursement_attribute
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, " ") //p_spend_qualifier_indicator
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(7, " ") //GC_SPACE
        l_result_string += "0" //p_pvs_performed_indicator
        l_result_string += T_u.d2s(T_u.s2d(i_merged_vts_log_transaction.get_field("F7"), "MMDDhhmmss"), "MMdd")
        //d2s
        l_result_string += T_u.d2s(T_u.s2d(i_merged_vts_log_transaction.get_field("F7"), "MMDDhhmmss"), "HHmmss")
        //d2s
        l_result_string += T_u.format_cobol_number(i_merged_vts_log_transaction.get_field("F61.1")).padLeft(12, "0")
        //p_transaction_other_amount
        l_result_string += i_merged_vts_log_transaction.get_field("F62.3").padRight(2, " ") //p_downgrade_reason_code
        l_result_string += i_merged_vts_log_transaction.get_field("F62.1").padRight(1, " ") //p_auth_characteristics_ind
        l_result_string += i_merged_vts_log_transaction.get_resp().get_field("MTI").padRight(4, " ")
        //p_response_message_type
        l_result_string += i_merged_vts_log_transaction.get_field("F23").padLeft(3, "0") //p_card_sequence_number
        l_result_string += i_merged_vts_log_transaction.get_field("F14").padLeft(4, "0") //
        l_result_string += i_merged_vts_log_transaction.get_field("F44.5").padRight(1, "0") //p_cvv_result_code
        l_result_string += "9" //p_settlement_service_selected
        l_result_string += "0" //p_irf_option
        l_result_string += i_merged_vts_log_transaction.get_field("F60.10").padRight(1, "0") //p_moto_indicator
        l_result_string += i_merged_vts_log_transaction.get_field("F63.18").padRight(2, " ")
        //p_merchant_volume_indicator
        l_result_string += i_merged_vts_log_transaction.get_field("F126.19").padRight(1, " ") //p_dcc_indicator
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(3, " ") //p_fee_program_indicator
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(6, " ") //GC_SPACE
        l_result_string += "0"
        return l_result_string
    }

    @I_black_box("error")
    String make_v22200_string(T_merged_vts_log_transaction i_merged_vts_log_transaction, Integer i_line_number) {
        String l_result_string = T_app_const.GC_EMPTY_STRING
        l_result_string += "3300"
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padLeft(6, "0").substring(0, 6) //BIN
        l_result_string += "400082SMSRAWDATA"
        l_result_string += String.valueOf(i_line_number).padLeft(8, "0")
        l_result_string += "V22200" //record type
        l_result_string += "I" //p_issuer_acquirer_indicator
        l_result_string += i_merged_vts_log_transaction.get_field("F62.20").padRight(10, " ") //p_mvv_code
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, " ") //p_remote_terminal_indicator
        l_result_string += "S".padRight(1, " ") //p_charge_indicator
        l_result_string += "F".padRight(2, " ") //p_product_id
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(2, " ") //p_business_application_identifier
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(1, " ") //p_source_of_funds
        l_result_string += T_app_const.GC_EMPTY_STRING.padRight(2, " ") //p_product_subtype
        l_result_string += "P".padRight(1, " ") //p_account_funding_source
        l_result_string += i_merged_vts_log_transaction.get_field("F32").padRight(10, "0").substring(0, 10)
        //p_affiliate_bin
        l_result_string += T_u.d2s(T_u.s2d(i_merged_vts_log_transaction.get_field("F15"), "MMdd"), "MMddyy").padLeft(6, " ")
        //settlement date
        l_result_string += i_merged_vts_log_transaction.get_field("F62.2").padRight(15, " ") //p_transaction_identifier
        l_result_string += i_merged_vts_log_transaction.get_field("F62.3").padRight(4, " ") //p_validation_code
        l_result_string += i_merged_vts_log_transaction.get_field("F37").padRight(12, " ")
        //p_retrieval_reference_number
        l_result_string += i_merged_vts_log_transaction.get_field("F11").padRight(6, " ") //p_trace_number
        l_result_string += i_merged_vts_log_transaction.get_field("H10").padLeft(4, "0") //p_batch_number
        l_result_string += i_merged_vts_log_transaction.get_req().get_field("MTI").padRight(4, " ")
        //p_request_message_type
        l_result_string += i_merged_vts_log_transaction.get_field("F39").padRight(2, " ") //p_response_code
        l_result_string += i_merged_vts_log_transaction.get_field("F3").padRight(6, " ") //p_processing_code
        l_result_string += i_merged_vts_log_transaction.get_field("F2").padRight(19, "0") //p_card_number
        l_result_string += T_u.format_cobol_number(i_merged_vts_log_transaction.get_field("F6")).padLeft(12, "0")
        //p_transaction_amount
        l_result_string += i_merged_vts_log_transaction.get_field("F51").padRight(3, " ") //p_currency_code_transaction
        l_result_string += "0".padLeft(4, " ") //GC_SMS_LINE_END
        return l_result_string
    }

}
