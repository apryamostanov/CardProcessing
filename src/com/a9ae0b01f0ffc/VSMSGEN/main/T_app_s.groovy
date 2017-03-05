package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.commons.static_string.T_static_string_builder

class T_app_s {

    static T_app_context x() {
        return (T_app_context)T_app_context.get_context()
    }

    static T_app_commons c() {
        return x().get_commons()
    }

    static final T_static_string_builder s() {
        return T_app_const.GC_STATIC_STRING_BUILDER
    }

    static T_trxn_config t() {
        return x().get_trxn_config()
    }

}
