package a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.exceptions.E_application_exception
import com.a9ae0b01f0ffc.implementation.T_class_loader

import java.text.SimpleDateFormat

class T_shortcuts {

    static T_app_commons c() {
        return T_app_context.getInstance().p_commons_thread_local.get()
    }

    static T_class_loader ioc() {
        return T_app_context.getInstance().p_ioc_thread_local.get()
    }

    @I_black_box
    static String d2s(Date i_date, String i_format) {
        String l_result_string = T_s.c().GC_EMPTY_STRING
        if (i_date != T_s.c().GC_NULL_OBJ_REF) {
            SimpleDateFormat l_simple_date_format = new SimpleDateFormat(i_format)
            l_result_string = l_simple_date_format.format(i_date)
        }
        l_result_string = l_result_string.padRight(Math.max(i_format.length(), l_result_string.length()), T_s.c().GC_SPACE)
        return l_result_string
    }

    @I_black_box
    static Date s2d(String i_date, String i_format) {
        final Integer LC_NOT_EXISTS = -1
        String l_format = i_format
        String l_date = T_s.nvl(i_date, new Date())
        Date l_result_date
        if (l_format.toLowerCase().indexOf("yyyy") == LC_NOT_EXISTS) {
            l_format += "yyyy"
            l_date += d2s(new Date(), "yyyy")
        } else if (l_format.toLowerCase().indexOf("yy") == LC_NOT_EXISTS) {
            l_format += "yy"
            l_date += d2s(new Date(), "yy")
        }
        if (l_date != T_s.c().GC_NULL_OBJ_REF) {
            SimpleDateFormat l_simple_date_format = new SimpleDateFormat(l_format)
            l_result_date = l_simple_date_format.parse(l_date)
        } else {
            throw new E_application_exception(T_s.s().UNABLE_TO_CONVERT_TEXT_TO_DATE, i_date, i_format)
        }
        return l_result_date
    }

    @I_black_box
    static String format_cobol_number(String i_number) {
        if (i_number == T_s.c().GC_EMPTY_STRING) {
            return "{"
        }
        int l_size = i_number.size()
        String l_result = i_number
        String l_last_char = l_result.substring(l_size - T_s.c().GC_ONE_CHAR, l_size)
        l_result = l_result.substring(T_s.c().GC_FIRST_CHAR, l_size - T_s.c().GC_ONE_CHAR)
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

}
