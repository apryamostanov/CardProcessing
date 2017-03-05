package com.a9ae0b01f0ffc.VSMSGEN.implementation

import com.a9ae0b01f0ffc.black_box.annotations.I_black_box
import com.a9ae0b01f0ffc.VSMSGEN.main.T_app_const
import com.a9ae0b01f0ffc.VSMSGEN.main.T_u
import groovy.transform.Canonical

@Canonical
class T_merged_vts_log_transaction {

    T_vts_log_transaction p_req = T_app_const.GC_NULL_OBJ_REF as T_vts_log_transaction
    T_vts_log_transaction p_resp = T_app_const.GC_NULL_OBJ_REF as T_vts_log_transaction

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
        return T_u.coalesce(p_req.get_field(i_field_name), p_resp.get_field(i_field_name), T_app_const.GC_EMPTY_STRING)
    }


}
