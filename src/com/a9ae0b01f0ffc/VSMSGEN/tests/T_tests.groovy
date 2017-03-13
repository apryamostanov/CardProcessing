package com.a9ae0b01f0ffc.VSMSGEN.tests

import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_visa_recon_generator
import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_vts_log_parser
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_4_const
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_5_context
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_7_main
import org.junit.Test

class T_tests {

    static final String PC_CONFIG_FILE_NAME =  "./src/com/a9ae0b01f0ffc/VSMSGEN/conf/commons.conf"

    @Test
    void test_001() {
        T_vsms_base_5_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_s.l().log_info(T_app_s.s().HELLO_WORLD)
    }

    @Test
    void test_002() {
        String l_test_string = "F23      Card Sequence Number       001                           001                          "
        assert "F23" == l_test_string.substring(T_vsms_base_4_const.GC_FIRST_CHAR, l_test_string.indexOf(" "))
    }

    @Test
    void test_003() {
        String l_test_string = "BMP      BitMap                          FEFE6681A8E4FA140000000011000000"
        assert "FEFE6681A8E4FA140000000011000000" == l_test_string.substring(41)
    }

    @Test
    void test_004() {
        T_vsms_base_5_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_vts_log_parser l_vts_log_parser = new T_vts_log_parser()
        l_vts_log_parser.parse_vts_log("C:\\Users\\anton\\IdeaProjects\\CardProcessing\\src\\com.a9ae0b01f0ffc\\VSMSGEN\\tests\\data\\vts.log")
    }

    @Test
    void test_005() {
        T_vsms_base_5_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        new T_vts_log_parser().process_line("test")
        T_s.l().print_stats()
    }

    @Test
    void test_006() {
        T_vsms_base_5_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_vts_log_to_ctf(T_vsms_base_7_main.c().GC_VTS_LOG_FILE, T_vsms_base_7_main.c().GC_CTF_FILE)
    }

}
