package com.a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.card_processing.implementation.T_visa_recon_generator

class T_main {

    public static void main(String... i_args) {
        T_app_s.x().init_custom("./conf/commons.conf")
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_vts_log_to_ctf(T_app_s.c().GC_VTS_LOG_FILE, T_app_s.c().GC_CTF_FILE)
    }

}
