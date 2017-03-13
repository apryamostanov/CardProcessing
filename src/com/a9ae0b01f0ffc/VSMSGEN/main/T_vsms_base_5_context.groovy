package com.a9ae0b01f0ffc.VSMSGEN.main

import com.a9ae0b01f0ffc.VSMSGEN.conf.T_vsms_conf
import com.a9ae0b01f0ffc.black_box.main.T_logging_base_5_context
import com.a9ae0b01f0ffc.commons.implementation.main.T_common_base_2_context

class T_vsms_base_5_context extends T_vsms_base_4_const{

    protected static ThreadLocal<T_vsms_base_5_context> p_context_thread_local = new ThreadLocal<T_vsms_base_5_context>()
    private T_vsms_conf p_commons = GC_NULL_OBJ_REF as T_vsms_conf

    static {
        p_context_thread_local.set(new T_vsms_base_5_context())
    }

    static void init_custom(String i_commons_conf_file_name) {
        p_context_thread_local.get().p_commons = new T_vsms_conf(i_commons_conf_file_name)
        T_common_base_2_context.init_custom(i_commons_conf_file_name)
        T_logging_base_5_context.init_custom(c().GC_BLACK_BOX_CONFIG)
    }

    static T_vsms_conf c() {
        return ((T_vsms_base_5_context) p_context_thread_local.get()).p_commons
    }

    static T_vsms_base_5_context get_context() {
        return p_context_thread_local.get()
    }

}
