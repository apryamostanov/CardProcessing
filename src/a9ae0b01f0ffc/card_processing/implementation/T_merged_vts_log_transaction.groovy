package a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.main.T_s
import groovy.transform.Canonical

import static a9ae0b01f0ffc.card_processing.main.T_app_commons.*

@Canonical
class T_merged_vts_log_transaction {

    T_vts_log_transaction p_req = T_s.c().GC_NULL_OBJ_REF as T_vts_log_transaction
    T_vts_log_transaction p_resp = T_s.c().GC_NULL_OBJ_REF as T_vts_log_transaction

    @I_black_box
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
        return T_s.nvl(T_s.nvl(p_req.get_field(i_field_name), p_resp.get_field(i_field_name)), T_s.c().GC_EMPTY_STRING)
    }


}
