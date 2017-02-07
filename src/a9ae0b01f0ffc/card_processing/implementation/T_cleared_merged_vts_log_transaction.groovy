package a9ae0b01f0ffc.card_processing.implementation

import com.a9ae0b01f0ffc.black_box.implementation.annotations.I_black_box
import com.a9ae0b01f0ffc.black_box.main.T_s
import groovy.transform.Canonical

@Canonical
class T_cleared_merged_vts_log_transaction {

    T_merged_vts_log_transaction p_0110 = T_s.c().GC_NULL_OBJ_REF as T_merged_vts_log_transaction
    T_merged_vts_log_transaction p_0210 = T_s.c().GC_NULL_OBJ_REF as T_merged_vts_log_transaction
    T_merged_vts_log_transaction p_0230 = T_s.c().GC_NULL_OBJ_REF as T_merged_vts_log_transaction

    @I_black_box("error")
    T_merged_vts_log_transaction get_0110() {
        return p_0110
    }

    @I_black_box("error")
    void set_0110(T_merged_vts_log_transaction i_0110) {
        this.p_0110 = i_0110
    }

    @I_black_box("error")
    T_merged_vts_log_transaction get_0210() {
        return p_0210
    }

    @I_black_box("error")
    void set_0210(T_merged_vts_log_transaction i_0210) {
        this.p_0210 = i_0210
    }

    @I_black_box("error")
    T_merged_vts_log_transaction get_0230() {
        return p_0230
    }

    @I_black_box("error")
    void set_0230(T_merged_vts_log_transaction i_0230) {
        this.p_0230 = i_0230
    }

    @I_black_box("error")
    String get_field(String i_field_name) {
        return T_s.nvl(T_s.nvl(T_s.nvl(p_0110.get_field(i_field_name), p_0210.get_field(i_field_name)), p_0230.get_field(i_field_name)), T_s.c().GC_EMPTY_STRING)
    }

}
