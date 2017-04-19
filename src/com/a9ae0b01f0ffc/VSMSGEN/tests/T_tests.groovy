package com.a9ae0b01f0ffc.VSMSGEN.tests

import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_visa_recon_generator
import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_vts_log_parser
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_4_const
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_5_context
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_7_main
import com.a9ae0b01f0ffc.commons.implementation.main.T_common_base_3_utils
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
        l_visa_recon_generator.convert_sim_log_to_ctf(T_vsms_base_7_main.c().GC_VTS_LOG_FILE, T_vsms_base_7_main.c().GC_VTS_CTF_FILE, T_vsms_base_4_const.VSMSGEN_CONVERT_VTS)
        T_vsms_base_6_util.l().log_info(T_vsms_base_6_util.s.Process_is_complete)
        T_vsms_base_6_util.l().flush()
        //T_logging_base_6_util.l().print_stats()
    }

    @Test
    void test_007() {
        String field_map_csv = "\"000 Message Type\",\"002 Primary Account Number\",\"003 Processing code\",\"004 Amount, Transaction\",\"007 Transmission Date and Time\",\"009 Conversion Rate, Settlement\",\"010 Conversion Rate, Cardholder Billing\",\"011 System Trace Audit Number\",\"012 Time, Local transaction\",\"013 Date, Local transaction\",\"014 Date, Expiry\",\"015 Date, Settlement\",\"016 Date, Conversion\",\"018 Merchant's type\",\"019 Acquiring Institution Country Code\",\"020 PAN Extended, Country Code\",\"022 Point Of Service Entry Mode\",\"025 Point Of Service Condition Code\",\"032 Acquiring Institution ID Code\",\"035 Track 2 Data\",\"037 Retrieval Reference Number\",\"038 Authorisation Identification Response\",\"039 Response Code\",\"041 Card Acceptor Terminal Identification\",\"042 Card Acceptor Identification Code\",\"043 Card Acceptor Name/Location\",\"044 Additional Response Data\",\"049 Currency Code, Transaction\",\"050 Currency Code, Settlement\",\"051 Currency Code, Cardholder Billing\",\"052 PIN Data\",\"053 Security Related Control Information\",\"054 Additional Amounts\",\"059 National POS Geographic Data\",\"060 Additional POS Information\",\"062.01 Reserved Private\",\"062.02 Reserved Private\",\"063 VIP Private-Use\",\"090 Original Data Elements\",\"120 Original Message Type\""

        field_map_csv = field_map_csv.substring(1, field_map_csv.length()-1)
        String[] field_map = field_map_csv.split('","')

        for( String values : field_map ) {
            System.out.println(values)
        }
    }

    @Test
    void test_008() {
        String l_field_name = "F126.10.4"
        Integer l_subfield_position = l_field_name.indexOf('.')

        if (l_field_name == "MTI")
            l_field_name = "F000"

        if ((l_field_name.substring(0,1) == "F") && (l_subfield_position >= T_vsms_base_4_const.GC_ZERO)){
            String l_field = l_field_name.substring(1, l_subfield_position).padLeft(3, '0')
            String l_subfield = l_field_name.substring(l_subfield_position + 1)
            if (l_subfield.indexOf('.') >= T_vsms_base_4_const.GC_ZERO){  // This is F055 subfield with by-byte description
                String l_ssubfield = l_subfield.substring(l_subfield.indexOf('.') + 1)
                l_subfield = l_subfield.substring(0, l_subfield.indexOf('.')).padLeft(2, '0')
                l_subfield = l_subfield + "." + l_ssubfield.padLeft(2, '0')
            } else {
                l_subfield = l_subfield.padLeft(2, '0')
            }
            l_field_name = "F" + l_field + "." + l_subfield
        } else {
            String l_field = l_field_name.substring(1)
            l_field_name = "F" + l_field.padLeft(3, '0')
        }

        System.out.println(l_field_name)
    }

    @Test
    void test_009() {
        String l_result_string = T_common_base_3_utils.d2s(T_common_base_3_utils.s2d("1212", "MMdd"), "MMddyy").padLeft(6, " ")
        System.out.println(l_result_string)
        String l_date = new Date().format("MMdd")
        System.out.println(l_date)
    }

    @Test
    void test_010() {
        String values_csv = "=\"0200\",=\"4172533760067342\",=\"000000\",000000000005,,,=\"04/04 07:25.54\",=\"\",=\"\",=\"000985\",=\"07:25.40\",=\"04/04\",=\"04/04\",=\"04/04\",=\"4784\",=\"710\",=\"051\",=\"00\",=\"12345678901\",\"4172533760067342D22032211921237601002\",=\"709407000985\",=\"\",=\"\",\"SCOTIABANK     \",\"BROADWAY & OAK           VANCOUVER  BCZA\",=\"\",=\"710\",=\"710\",\"1111        \",=\"2001010100000000\",=\"710\",\"5C00\",=\"\",=\"0000000000\",=\"170404\",=\"00\",=\"000000000005\",=\"000000000000\",=\"710\",\"0BE98DF1B568E53C\",=\"80\",\"E0E080\",=\"010302\",\"005A\",\"6FFDA9BD\",=\"000000\",=\"\""

        /*        ="0200",="4264371020070593",="000000",000000002000,000000000016,000000000016,="04/04 06:52.21",="60008333",="60008333",="000983",="13:52.16",="04/04",="04/04",="04/04",="8011",="784",="010",="59",="454183",="",="709406000983",="",="","001070000112   ","Med Shop                 Dubai        AE",="",="784",="784","1111        ",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="000000","10 123"
        ="0210",="4264371020070593",="000000",000000002000,,,="04/04 06:52.21",="",="",="000983",="",="",="04/04",="",="",="784",="",="59",="454183",="",="709406000983",="",="14","001070000112   ",="",="",="784",="784","1111        ",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="",="",=""
        ="0200",="4172533760067342",="000000",000000000005,,,="04/04 07:25.54",="",="",="000985",="07:25.40",="04/04",="04/04",="04/04",="4784",="710",="051",="00",="12345678901","4172533760067342D22032211921237601002",="709407000985",="",="","SCOTIABANK     ","BROADWAY & OAK           VANCOUVER  BCZA",="",="710",="710","1111        ",="2001010100000000",="710","5C00",="",="0000000000",="170404",="00",="000000000005",="000000000000",="710","0BE98DF1B568E53C",="80","E0E080",="010302","005A","6FFDA9BD",="000000",=""
        ="0210",="4172533760067342",="000000",000000000005,,,="04/04 07:25.54",="",="",="000985",="",="",="04/04",="",="",="710",="",="00",="12345678901",="",="709407000985",="",="05","SCOTIABANK     ",="","    2   2  ",="710",="710","1111        ",="",="",="","32332722D7CE37683030",="",="",="",="",="",="",="",="",="",="","005A",="",="",=""
*/

        values_csv = values_csv.replace("\",",'|')
        values_csv = values_csv.replace("=\"",'')
        values_csv = values_csv.replace(",",'|')
        values_csv = values_csv.replace("\"",'')

        System.out.println(values_csv)

        String[] values_map = values_csv.split("\\|")

       for( String values : values_map ) {
            System.out.println(values)
        }

    }
    @Test
    void test_011() {
        String l_field_value = "    2   2                    "
        String l_f043_1 = l_field_value.substring(4, 5)
        String l_f043_2 = l_field_value.substring(8, 9)

        System.out.println("[" + l_f043_1 + "]")
        System.out.println("[" + l_f043_2 + "]")

    }

    @Test
    void test_012() {
        T_vsms_base_5_context.get_context().init_custom(PC_CONFIG_FILE_NAME)
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_sim_log_to_ctf(T_vsms_base_7_main.c().GC_FINSIM_CSV_FILE, T_vsms_base_7_main.c().GC_FINSIM_CTF_FILE, T_vsms_base_4_const.VSMSGEN_CONVERT_FINSIM)
        T_vsms_base_6_util.l().log_info(T_vsms_base_6_util.s.Process_is_complete)
        T_vsms_base_6_util.l().flush()
        //T_logging_base_6_util.l().print_stats()
    }
}

