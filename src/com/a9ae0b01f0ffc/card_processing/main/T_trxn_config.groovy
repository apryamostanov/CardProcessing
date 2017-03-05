package com.a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.commons.main.T_common_commons

class T_trxn_config extends T_common_commons{

    static String GC_CURRENCY_DECDIGITS_0
    static String GC_CURRENCY_DECDIGITS_3

    T_trxn_config(String i_trxn_config_file_name) {
        super(i_trxn_config_file_name)
        GC_CURRENCY_DECDIGITS_0 = GC_CONST_CONF.GC_CURRENCY_DECDIGITS_0(GC_CURRENCY_DECDIGITS_0)
        GC_CURRENCY_DECDIGITS_3 = GC_CONST_CONF.GC_CURRENCY_DECDIGITS_3(GC_CURRENCY_DECDIGITS_3)
    }

}
