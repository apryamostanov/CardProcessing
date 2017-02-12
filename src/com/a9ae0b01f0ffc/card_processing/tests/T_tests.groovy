package com.a9ae0b01f0ffc.card_processing.tests

import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.card_processing.implementation.T_visa_recon_generator
import com.a9ae0b01f0ffc.card_processing.implementation.T_vts_log_parser
import com.a9ae0b01f0ffc.card_processing.main.T_app_const
import com.a9ae0b01f0ffc.card_processing.main.T_app_context
import com.a9ae0b01f0ffc.card_processing.main.T_app_s
import com.a9ae0b01f0ffc.card_processing.main.T_u
import org.junit.Test

class T_tests {

    static final String PC_CONFIG_FILE_NAME =  "./src/com/a9ae0b01f0ffc/card_processing/conf/commons.conf"

    @Test
    void test_001() {
        T_app_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_s.l().log_info(T_app_s.s().HELLO_WORLD)
    }

    @Test
    void test_002() {
        String l_test_string = "F23      Card Sequence Number       001                           001                          "
        assert "F23" == l_test_string.substring(T_app_const.GC_FIRST_CHAR, l_test_string.indexOf(" "))
    }

    @Test
    void test_003() {
        String l_test_string = "BMP      BitMap                          FEFE6681A8E4FA140000000011000000"
        assert "FEFE6681A8E4FA140000000011000000" == l_test_string.substring(41)
    }

    @Test
    void test_004() {
        T_app_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_vts_log_parser l_vts_log_parser = new T_vts_log_parser()
        l_vts_log_parser.parse_vts_log("C:\\Users\\anton\\IdeaProjects\\CardProcessing\\src\\com.a9ae0b01f0ffc\\card_processing\\tests\\data\\vts.log")
    }

    @Test
    void test_005() {
        T_app_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        new T_vts_log_parser().process_line("test")
        T_s.l().print_stats()
    }

    @Test
    void test_006() {
        com.a9ae0b01f0ffc.black_box_base.main.T_s.x().init_custom("./src/com/a9ae0b01f0ffc/card_processing/conf/logger_debug/logger_commons.conf")
        T_s.x().init_custom("./src/com/a9ae0b01f0ffc/card_processing/conf/logger_commons.conf")
        T_app_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_vts_log_to_ctf(T_app_s.c().GC_VTS_LOG_FILE, T_app_s.c().GC_CTF_FILE)
        //com.a9ae0b01f0ffc.black_box_base.main.T_s.l().print_stats()
        T_s.l().print_stats()
    }

    @Test
    void test_007() {
        com.a9ae0b01f0ffc.black_box_base.main.T_s.x().init_custom("./src/com/a9ae0b01f0ffc/card_processing/conf/logger_debug/logger_commons.conf")
        //T_s.x().init_custom("./src/com/a9ae0b01f0ffc/card_processing/conf/logger_commons.conf")
        System.out.println(T_u.format_cobol_number("0000000000000"))
        //com.a9ae0b01f0ffc.black_box_base.main.T_s.l().print_stats()
        //T_s.l().print_stats()
    }

}
