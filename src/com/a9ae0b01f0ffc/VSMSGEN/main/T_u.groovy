package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.commons.main.T_common_utils

class T_u extends T_common_utils{

    @I_black_box("error")
    static String format_cobol_number(String i_number) {
        if (i_number == T_app_const.GC_EMPTY_STRING) {
            return "{"
        }
        int l_size = i_number.size()
        String l_result = i_number
        String l_last_char = l_result.substring(l_size - T_app_const.GC_ONE_CHAR, l_size)
        l_result = l_result.substring(T_app_const.GC_FIRST_CHAR, l_size - T_app_const.GC_ONE_CHAR)
        l_last_char = l_last_char.replace("0", "{")
        l_last_char = l_last_char.replace("1", "A")
        l_last_char = l_last_char.replace("2", "B")
        l_last_char = l_last_char.replace("3", "C")
        l_last_char = l_last_char.replace("4", "D")
        l_last_char = l_last_char.replace("5", "E")
        l_last_char = l_last_char.replace("6", "F")
        l_last_char = l_last_char.replace("7", "G")
        l_last_char = l_last_char.replace("8", "H")
        l_last_char = l_last_char.replace("9", "I")
        l_result += l_last_char
        return l_result
    }

    @I_black_box("error")
    static String adjust_decdigits_V22260(String i_amount, String i_currency) {
        if (i_currency == T_app_const.GC_EMPTY_STRING || i_amount == T_app_const.GC_EMPTY_STRING) {
            return T_app_const.GC_EMPTY_STRING
        }
        Integer l_currency_found
        Integer l_adjusted_amount = new Integer(i_amount)
        l_currency_found = T_trxn_config.GC_CURRENCY_DECDIGITS_0.indexOf(i_currency)
        if (l_currency_found >= 0) l_adjusted_amount *= 100
        l_currency_found = T_trxn_config.GC_CURRENCY_DECDIGITS_3.indexOf(i_currency)
        if (l_currency_found >= 0) l_adjusted_amount /= 10
        return l_adjusted_amount.toString()
    }

}
