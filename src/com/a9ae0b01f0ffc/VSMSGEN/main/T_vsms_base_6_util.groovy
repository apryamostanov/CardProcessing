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


    static String adjust_decdigits_V22260(String i_amount, String i_currency) {
        if (i_currency == GC_EMPTY_STRING || i_amount == GC_EMPTY_STRING) {
            return GC_EMPTY_STRING
        }
        Integer l_currency_found
        Integer l_adjusted_amount = new Integer(i_amount)
        l_currency_found = c().GC_CURRENCY_DECDIGITS_0.indexOf(i_currency)
        if (l_currency_found >= GC_ZERO) l_adjusted_amount *= 100
        l_currency_found = c().GC_CURRENCY_DECDIGITS_3.indexOf(i_currency)
        if (l_currency_found >= GC_ZERO) l_adjusted_amount /= 10
        return l_adjusted_amount.toString()
    }

    @I_black_box("error")
    public static String normalize_field_naming(String l_field_name) {
        Integer l_subfield_position = l_field_name.indexOf('.')

        /* Apply unified field naming convention: Fxxx, Fxxx.yy, Fxxx.yy.zz */
        if ((l_field_name.substring(0, 1) == "F") && (l_subfield_position >= GC_ZERO)) {
            String l_field = l_field_name.substring(1, l_subfield_position).padLeft(3, '0')
            String l_subfield = l_field_name.substring(l_subfield_position + 1)
            if (l_subfield.indexOf('.') >= GC_ZERO) {  /* This is F055 subfield with sub-subfield, Fxxx.yy.zz case */
                String l_ssubfield = l_subfield.substring(l_subfield.indexOf('.') + 1)
                l_subfield = l_subfield.substring(0, l_subfield.indexOf('.')).padLeft(2, '0')
                l_subfield = l_subfield + "." + l_ssubfield.padLeft(2, '0')
            } else { /* This is Fxxx.yy subfield case */
                l_subfield = l_subfield.padLeft(2, '0')
            }
            l_field_name = "F" + l_field + "." + l_subfield
        } else { /* This is atomic field, Fxxx case */
            String l_field = l_field_name.substring(1)
            l_field_name = "F" + l_field.padLeft(3, '0')
        }

        return l_field_name

    }

}
