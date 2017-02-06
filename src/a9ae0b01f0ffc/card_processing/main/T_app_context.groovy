package a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.black_box.main.T_context
import com.a9ae0b01f0ffc.implementation.T_class_loader

@Singleton
class T_app_context {

    ThreadLocal<T_app_commons> p_commons_thread_local = new ThreadLocal<T_app_commons>()
    ThreadLocal<T_class_loader> p_ioc_thread_local = new ThreadLocal<T_class_loader>()

    void init_custom(String i_conf_file_name) {
        p_commons_thread_local.set(new T_app_commons())
        p_commons_thread_local.get().init_custom(i_conf_file_name)
        p_ioc_thread_local.set(new T_class_loader(p_commons_thread_local.get().GC_CLASSES_CONF))
        T_context.getInstance().init_custom(p_commons_thread_local.get().GC_BLACK_BOX_CONFIG)
    }

}
