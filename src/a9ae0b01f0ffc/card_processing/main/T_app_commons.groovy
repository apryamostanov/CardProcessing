package a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.configuration_utility.implementation.T_conf

class T_app_commons {

    static T_conf GC_CONST_CONF
    static String GC_CLASSES_CONF
    static String GC_BLACK_BOX_CONFIG
    static String GC_VSMS_RECON_VERSION
    static String GC_CTF_OUT_FILE_NAME

    static void init_custom(String i_conf_file_name) {
        GC_CONST_CONF = new T_conf(i_conf_file_name)
        GC_CLASSES_CONF = GC_CONST_CONF.GC_CLASSES_CONF(GC_CLASSES_CONF)
        GC_BLACK_BOX_CONFIG = GC_CONST_CONF.GC_BLACK_BOX_CONFIG(GC_BLACK_BOX_CONFIG)
        GC_VSMS_RECON_VERSION = GC_CONST_CONF.GC_VSMS_RECON_VERSION(GC_VSMS_RECON_VERSION)
        GC_CTF_OUT_FILE_NAME = GC_CONST_CONF.GC_CTF_OUT_FILE_NAME(GC_CTF_OUT_FILE_NAME)
    }

}
