package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.implementation.T_logger
import com.a9ae0b01f0ffc.black_box.main.T_logging_base_5_context

class T_vsms_base_6_util extends T_vsms_base_5_context{

    @I_black_box("error")
    static T_logger l() {
        return T_logging_base_5_context.l()
    }

    @I_black_box("error")
    static String format_cobol_number(String i_number) {
        if (i_number == GC_EMPTY_STRING) {
            return "{"
        }
        int l_size = i_number.size()
        String l_result = i_number
        String l_last_char = l_result.substring(l_size - GC_ONE_CHAR, l_size)
        l_result = l_result.substring(GC_FIRST_CHAR, l_size - GC_ONE_CHAR)
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
        if (i_currency == GC_EMPTY_STRING || i_amount == GC_EMPTY_STRING) {
            return GC_EMPTY_STRING
        }
        Integer l_currency_found
        Integer l_adjusted_amount = new Integer(i_amount)
        l_currency_found = c().GC_CURRENCY_DECDIGITS_0.indexOf(i_currency)
        if (l_currency_found >= 0) l_adjusted_amount *= 100
        l_currency_found = c().GC_CURRENCY_DECDIGITS_3.indexOf(i_currency)
        if (l_currency_found >= 0) l_adjusted_amount /= 10
        return l_adjusted_amount.toString()
    }

}
