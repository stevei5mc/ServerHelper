package cn.stevei5mc.serverhelper.common.utils;

import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceControlsType;
import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceOsType;
import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceUiType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDeviceInfoUtilTest {

    @ParameterizedTest(name = "osId={0} -> {1}")
    @CsvSource({
            "1, ANDROID", "2, IOS", "3, MACOS", "4, FIRE_OS", "5, GEAR_VR", "6, HOLOLENS", "7, WINDOWS", "8, WINDOWS_X86", "9, DEDICATED",
            "10, TV_OS", "11, PLAY_STATION", "12, SWITCH", "13, XBOX", "14, WINDOWS_PHONE", "15, LINUX", "-114, UNKNOWN", "514, UNKNOWN"
    })
    @DisplayName("测试 getDeviceOS 方法")
    void testGetDeviceOS(int osId, PlayerDeviceOsType expected) {
        assertEquals(expected, PlayerDeviceInfoUtil.getDeviceOS(osId));
    }


    @ParameterizedTest(name = "uiId={0} -> {1}")
    @CsvSource({
            "0, CLASSIC", "1, POCKET", "-114, UNKNOWN",  "514, UNKNOWN"
    })
    void testGetPlayerUi(int uiId, PlayerDeviceUiType expected) {
        assertEquals(expected, PlayerDeviceInfoUtil.getPlayerUi(uiId));
    }

    @ParameterizedTest(name = "ctrlId={0} -> {1}")
    @CsvSource({
            "1, KEYBOARD", "2, TOUCH", "3, PAD", "4, MOTION_CONTROLLER", "-114, UNKNOWN",  "514, UNKNOWN"
    })
    void testGetDeviceControls(int ctrlId, PlayerDeviceControlsType expected) {
        assertEquals(expected, PlayerDeviceInfoUtil.getDeviceControls(ctrlId));
    }
}