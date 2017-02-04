package a9ae0b01f0ffc.card_processing.tests

import a9ae0b01f0ffc.card_processing.main.T_card_processing_context
import com.a9ae0b01f0ffc.black_box.main.T_s
import org.junit.Test

class T_tests {

    static final String PC_CONFIG_FILE_NAME = "C:\\Users\\anton\\IdeaProjects\\CardProcessing\\src\\a9ae0b01f0ffc\\card_processing\\conf\\commons.conf"

    @Test
    void test_001() {
        T_card_processing_context.getInstance().init_custom(PC_CONFIG_FILE_NAME)
        T_s.l().log_info(T_s.s().HELLO_WORLD)
    }

}
