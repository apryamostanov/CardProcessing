package com.a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.commons.main.T_common_commons


class T_app_commons extends T_common_commons{

    static String GC_SMS_CLASSES_CONF
    static String GC_BLACK_BOX_CONFIG
    static String GC_VSMS_RECON_VERSION
    static String GC_VTS_LOG_FILE
    static String GC_CTF_FILE
    static String GC_TRXN_CONFIG_FILE

    T_app_commons(String i_conf_file_name) {
        super(i_conf_file_name)
        GC_SMS_CLASSES_CONF = GC_CONST_CONF.GC_SMS_CLASSES_CONF(GC_SMS_CLASSES_CONF)
        GC_BLACK_BOX_CONFIG = GC_CONST_CONF.GC_BLACK_BOX_CONFIG(GC_BLACK_BOX_CONFIG)
        GC_VSMS_RECON_VERSION = GC_CONST_CONF.GC_VSMS_RECON_VERSION(GC_VSMS_RECON_VERSION)
        GC_VTS_LOG_FILE = GC_CONST_CONF.GC_VTS_LOG_FILE(GC_VTS_LOG_FILE)
        GC_CTF_FILE = GC_CONST_CONF.GC_CTF_FILE(GC_CTF_FILE)
        GC_TRXN_CONFIG_FILE = GC_CONST_CONF.GC_TRXN_CONFIG_FILE(GC_TRXN_CONFIG_FILE)
    }

}
