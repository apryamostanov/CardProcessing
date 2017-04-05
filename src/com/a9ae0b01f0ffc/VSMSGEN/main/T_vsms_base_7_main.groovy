package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_visa_recon_generator

class T_vsms_base_7_main extends T_vsms_base_6_util{

    public static void main(String... i_args) {
        //x().init_custom("./conf/commons.conf")
        init_custom("./src/com/a9ae0b01f0ffc/VSMSGEN/conf/commons.conf")
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_sim_log_to_ctf(c().GC_VTS_LOG_FILE, c().GC_VTS_CTF_FILE, VSMSGEN_CONVERT_VTS)
        l_visa_recon_generator.convert_sim_log_to_ctf(c().GC_FINSIM_CSV_FILE, c().GC_FINSIM_CTF_FILE, VSMSGEN_CONVERT_FINSIM)
        l().log_info(s.Process_is_complete)
        l().flush()
    }

}
