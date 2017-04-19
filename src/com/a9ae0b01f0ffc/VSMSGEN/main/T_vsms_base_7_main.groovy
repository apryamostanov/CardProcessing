package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_visa_recon_generator

class T_vsms_base_7_main extends T_vsms_base_6_util {

    public static void main(String... i_args) {
        init_custom("./conf/commons.conf")
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        String l_vsmsgen_mode = VSMSGEN_CONVERT_VTS
        String l_log_file
        String l_ctf_file

        if (i_args.length > 0) {
            l_vsmsgen_mode = i_args[0]
        }

        if (l_vsmsgen_mode == VSMSGEN_CONVERT_VTS) {
            l_log_file = c().GC_VTS_LOG_FILE
            l_ctf_file = c().GC_VTS_CTF_FILE
        } else {
            l_log_file = c().GC_FINSIM_CSV_FILE
            l_ctf_file = c().GC_FINSIM_CTF_FILE
        }

        l_visa_recon_generator.convert_sim_log_to_ctf(l_log_file, l_ctf_file, l_vsmsgen_mode)
        l().log_info(s.Process_is_complete)
        l().flush()
    }

}
