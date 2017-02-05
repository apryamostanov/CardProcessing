package a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.implementation.T_class_loader

class T_shortcuts {

    static T_app_commons c() {
        return T_app_context.getInstance().p_commons_thread_local.get()
    }

    static T_class_loader ioc() {
        return T_app_context.getInstance().p_ioc_thread_local.get()
    }

}
