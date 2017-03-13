package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.VSMSGEN.main.T_vsms_base_6_util
import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import groovy.transform.Canonical
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_merged_vts_log_transaction  extends T_vsms_base_6_util{

    T_vts_log_transaction p_req = GC_NULL_OBJ_REF as T_vts_log_transaction
    T_vts_log_transaction p_resp = GC_NULL_OBJ_REF as T_vts_log_transaction

    @I_black_box("error")
    T_vts_log_transaction get_req() {
        return p_req
    }

    @I_black_box("error")
    void set_req(T_vts_log_transaction i_req) {
        p_req = i_req
    }

    @I_black_box("error")
    T_vts_log_transaction get_resp() {
        return p_resp
    }

    @I_black_box("error")
    void set_resp(T_vts_log_transaction i_resp) {
        p_resp = i_resp
    }

    @I_black_box("error")
    String get_field(String i_field_name) {
        return coalesce(p_req.get_field(i_field_name), p_resp.get_field(i_field_name), GC_EMPTY_STRING)
    }


}
