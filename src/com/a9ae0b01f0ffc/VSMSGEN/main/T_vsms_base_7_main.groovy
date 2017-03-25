package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.VSMSGEN.implementation.T_visa_recon_generator

class T_vsms_base_7_main extends T_vsms_base_6_util{

    public static void main(String... i_args) {
        x().init_custom("./conf/commons.conf")
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_vts_log_to_ctf(c().GC_VTS_LOG_FILE, c().GC_CTF_FILE)
        l().log_info(s.Process_is_complete)
        l().flush()
    }

}
