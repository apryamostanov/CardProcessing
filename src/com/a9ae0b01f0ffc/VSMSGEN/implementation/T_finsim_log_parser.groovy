package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.annotations.I_fix_variable_scopes

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = false)
@I_fix_variable_scopes
class T_finsim_log_parser extends T_vsms_base_6_util {

    static Integer p_current_log_file_line_number = GC_ZERO

    ArrayList<T_sim_log_transaction> p_finsim_log_transactions = new ArrayList<T_sim_log_transaction>()

    T_sim_log_transaction p_transaction_original = GC_NULL_OBJ_REF as T_sim_log_transaction
    T_sim_log_transaction p_transaction_expanded = GC_NULL_OBJ_REF as T_sim_log_transaction

    ArrayList<String> p_sim_log_original_fields = new ArrayList<String>()
    ArrayList<String> p_sim_log_expanded_fields = new ArrayList<String>()

    @I_black_box("error")
    void expand_field_F043_map() {
        p_sim_log_expanded_fields.add("F043.01")
        p_sim_log_expanded_fields.add("F043.02")
        p_sim_log_expanded_fields.add("F043.03")
    }

    @I_black_box("error")
    void expand_field_F044_map() {
        p_sim_log_expanded_fields.add("F044.05")
        p_sim_log_expanded_fields.add("F044.08")
    }

    @I_black_box("error")
    void expand_field_F060_map() {
        p_sim_log_expanded_fields.add("F060.01")
        p_sim_log_expanded_fields.add("F060.02")
        p_sim_log_expanded_fields.add("F060.03")
        p_sim_log_expanded_fields.add("F060.04")
        p_sim_log_expanded_fields.add("F060.10")
    }

    @I_black_box("error")
    void fill_expanded_fields(String i_field_name) {
        if (p_sim_log_expanded_fields.contains(i_field_name) == GC_FALSE) {
            p_sim_log_expanded_fields.add(i_field_name)
            if (["F043", "F044", "F060"].contains(i_field_name)) {
                /* If we're here then we are going to expand the map with subfields used in VSMSGEN logic */
                switch (i_field_name) {
                    case "F043": expand_field_F043_map(); break
                    case "F044": expand_field_F044_map(); break
                    case "F060": expand_field_F060_map(); break
                }
            }
        }
    }

    @I_black_box("error")
    void parse_available_fields(String i_line) {
        /* Remove begin and end quotes and then split */
        String[] l_field_map = i_line.substring(1, i_line.length() - 1).split('","')

        for (String l_value : l_field_map) {
            String l_field_name = l_value.substring(GC_FIRST_CHAR, l_value.indexOf(GC_SPACE))
            p_sim_log_original_fields.add(normalize_field_naming("F" + l_field_name))
            fill_expanded_fields(normalize_field_naming("F" + l_field_name))
        }

    }

    @I_black_box("error")
    void parse_F043_value(String i_field_value) {
        if (i_field_value == GC_EMPTY_STRING)
            return
        p_transaction_expanded.add_field("F043.01", i_field_value.substring(0, 25))
        p_transaction_expanded.add_field("F043.02", i_field_value.substring(25, 38))
        p_transaction_expanded.add_field("F043.03", i_field_value.substring(38, 40))
    }

    @I_black_box("error")
    void parse_F044_value(String i_field_value) {
        i_field_value = i_field_value.padRight(25, ' ')
        p_transaction_expanded.add_field("F044.05", i_field_value.substring(4, 5))
        p_transaction_expanded.add_field("F044.08", i_field_value.substring(8, 9))
    }

    @I_black_box("error")
    void parse_F060_value(String i_field_value) {
        i_field_value = i_field_value.padRight(12, '0')
        p_transaction_expanded.add_field("F060.01", i_field_value.substring(0, 1))
        p_transaction_expanded.add_field("F060.02", i_field_value.substring(1, 2))
        p_transaction_expanded.add_field("F060.03", i_field_value.substring(2, 3))
        p_transaction_expanded.add_field("F060.04", i_field_value.substring(3, 4))
        p_transaction_expanded.add_field("F060.10", i_field_value.substring(11, 12))
    }

    @I_black_box("error")
    void parse_expanded_field_values(String i_field_name, String i_field_value) {
        p_transaction_expanded.add_field(i_field_name, i_field_value)
        switch (i_field_name) {
            case "F043": parse_F043_value(i_field_value); break
            case "F044": parse_F044_value(i_field_value); break
            case "F060": parse_F060_value(i_field_value); break
        }
    }

    @I_black_box("error")
    void parse_field_values(String i_line) {
        /* Perform some transformation and replacements for successful string split */
        i_line = i_line.replace("\",", '|')
        i_line = i_line.replace("=\"", '')
        i_line = i_line.replace(",", '|')
        i_line = i_line.replace("\"", '')
        i_line = i_line + "|"

        String[] l_values_map = i_line.split("\\|")
        Integer l_iterator = GC_ZERO

        for (String l_field_name : p_sim_log_original_fields) {
            if (l_iterator < l_values_map.length) {
                parse_expanded_field_values(l_field_name, l_values_map[l_iterator])
                p_transaction_original.add_field(l_field_name, l_values_map[l_iterator++])
            }
        }
    }

    @I_black_box("error")
    void process_line(String i_line) {
        if (p_current_log_file_line_number == GC_ZERO) { /* initial field map parsing */
            parse_available_fields(i_line)
        } else {
            p_transaction_original = new T_sim_log_transaction()
            p_transaction_expanded = new T_sim_log_transaction()
            p_transaction_original.set_log_line_number(p_current_log_file_line_number)
            p_transaction_expanded.set_log_line_number(p_current_log_file_line_number)
            parse_field_values(i_line)
            p_finsim_log_transactions.add(p_transaction_expanded)
        }
    }

    @I_black_box("error")
    ArrayList<T_sim_log_transaction> parse_log(String i_log_file_name) {
        new File(i_log_file_name).eachLine { String l_line ->
            process_line(l_line)
            p_current_log_file_line_number++
        }
        return p_finsim_log_transactions
    }

}
