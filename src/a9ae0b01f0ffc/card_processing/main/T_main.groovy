package a9ae0b01f0ffc.card_processing.main

import a9ae0b01f0ffc.card_processing.implementation.T_visa_recon_generator
import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box

class T_main {

    public static void main(String... i_args) {
        T_app_context.getInstance().init_custom("./conf/commons.conf")
        T_visa_recon_generator l_visa_recon_generator = new T_visa_recon_generator()
        l_visa_recon_generator.convert_vts_log_to_ctf(T_shortcuts.c().GC_CONST_CONF.GC_VTS_LOG_FILE_NAME, T_shortcuts.c().GC_CONST_CONF.GC_CTF_FILE_NAME)
    }

}
