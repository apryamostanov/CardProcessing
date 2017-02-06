package a9ae0b01f0ffc.card_processing.main

import com.a9ae0b01f0ffc.configuration_utility.implementation.T_conf
import com.a9ae0b01f0ffc.static_string.T_static_string_builder

import java.lang.management.ManagementFactory

class T_app_commons {

    static final Object GC_NULL_OBJ_REF = null
    static final String GC_EMPTY_STRING = ""
    static final String GC_SPACE = " "
    static final String GC_AT_CHAR = "@"
    static final String GC_COMMA = ","
    static final String GC_COLON = ":"
    static final Integer GC_EMPTY_SIZE = 0
    static final Integer GC_FIRST_INDEX = 0
    static final Integer GC_FIRST_CHAR = 0
    static final String GC_NEW_LINE = System.lineSeparator()
    static final String GC_PATH_SEPARATOR = File.separator
    static final String GC_POINT = "."
    static final Integer GC_ONE_CHAR = 1
    static final Boolean GC_TRUE = true
    static final Boolean GC_FALSE = false
    static final Integer GC_ZERO = 0
    static final String GC_DATE_FORMAT_UID = "yyyyMMddHHmmssSSS"
    static final Boolean GC_FILE_APPEND_YES = GC_TRUE
    static final String GC_SUBST_USERNAME = "%USERNAME%"
    static final String GC_SUBST_DATE = "%DATE%"
    static final String GC_SUBST_TIME = "%TIME%"
    static final String GC_SUBST_THREADID = "%THREADID%"
    static final String GC_SUBST_PROCESSID = "%PROCESSID%"
    static final String GC_USERNAME = System.getProperty("user.name")
    static final String GC_THREADID = Long.toString(Thread.currentThread().getId())
    static final String GC_PROCESSID = ManagementFactory.getRuntimeMXBean().getName().substring(GC_FIRST_CHAR, ManagementFactory.getRuntimeMXBean().getName().indexOf(GC_AT_CHAR))//When Java 9 comes: ProcessHandle.current().getPid()
    static final T_static_string_builder GC_STATIC_STRING_BUILDER = new T_static_string_builder()
    static final String GC_TRACE_SOURCE_ALL = "all"
    static final String GC_TRACE_SOURCE_PREDEFINED = "predefined"
    static final String GC_TRACE_SOURCE_RUNTIME = "runtime"
    static final String GC_TRACE_SOURCE_CONTEXT = "context"
    static final String GC_TRACE_SOURCE_EXCEPTION_TRACES = "exception_traces"
    static final String GC_TRACE_MASK_ALL = "all"
    static final String GC_TRACE_MASK_SENSITIVE = "sensitive"
    static final String GC_TRACE_MASK_ALL_EXCEPT_NON_SENSITIVE = "except_non_sensitive"
    static final String GC_TRACE_MASK_NONE = "none"
    static final String GC_FALSE_STRING = "false"
    static final String GC_TRUE_STRING = "true"
    static T_conf GC_CONST_CONF = GC_NULL_OBJ_REF as T_conf
    static String GC_CLASSES_CONF = GC_EMPTY_STRING
    static String GC_BLACK_BOX_CONFIG = GC_EMPTY_STRING
    static String GC_VSMS_RECON_VERSION = GC_EMPTY_STRING

    static void init_custom(String i_conf_file_name) {
        GC_CONST_CONF = new T_conf(i_conf_file_name)
        GC_CLASSES_CONF = GC_CONST_CONF.GC_CLASSES_CONF(GC_CLASSES_CONF)
        GC_BLACK_BOX_CONFIG = GC_CONST_CONF.GC_BLACK_BOX_CONFIG(GC_BLACK_BOX_CONFIG)
        GC_VSMS_RECON_VERSION = GC_CONST_CONF.GC_VSMS_RECON_VERSION(GC_VSMS_RECON_VERSION)
    }

}
