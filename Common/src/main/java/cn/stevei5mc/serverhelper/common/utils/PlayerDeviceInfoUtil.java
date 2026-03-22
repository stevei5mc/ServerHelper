package cn.stevei5mc.serverhelper.common.utils;

import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceControlsType;
import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceOsType;
import cn.stevei5mc.serverhelper.common.utils.player.PlayerDeviceUiType;

public class PlayerDeviceInfoUtil {

    /**
     * @param osId OS id
     * @return OS type
     */
    public static PlayerDeviceOsType getDeviceOS(int osId) {
        switch (osId) {
            case 1: return PlayerDeviceOsType.ANDROID;
            case 2: return PlayerDeviceOsType.IOS;
            case 3: return PlayerDeviceOsType.MACOS;
            case 4: return PlayerDeviceOsType.FIRE_OS;
            case 5: return PlayerDeviceOsType.GEAR_VR;
            case 6: return PlayerDeviceOsType.HOLOLENS;
            case 7: return PlayerDeviceOsType.WINDOWS;
            case 8: return PlayerDeviceOsType.WINDOWS_X86;
            case 9: return PlayerDeviceOsType.DEDICATED;
            case 10: return PlayerDeviceOsType.TV_OS;
            case 11: return PlayerDeviceOsType.PLAY_STATION;
            case 12: return PlayerDeviceOsType.SWITCH;
            case 13: return PlayerDeviceOsType.XBOX;
            case 14: return PlayerDeviceOsType.WINDOWS_PHONE;
            case 15: return PlayerDeviceOsType.LINUX;
            default: return PlayerDeviceOsType.UNKNOWN;
        }
    }

    /**
     * @param uiId UI type id
     * @return UI type
     */
    public static PlayerDeviceUiType getPlayerUi(int uiId) {
        switch (uiId) {
            case 0: return PlayerDeviceUiType.CLASSIC;
            case 1: return PlayerDeviceUiType.POCKET;
            default: return PlayerDeviceUiType.UNKNOWN;
        }
    }

    /**
     * @param ctrlId Control type id
     * @return  Control type
     */
    public static PlayerDeviceControlsType getDeviceControls(int ctrlId) {
        switch (ctrlId) {
            case 1: return PlayerDeviceControlsType.KEYBOARD;
            case 2: return PlayerDeviceControlsType.TOUCH;
            case 3: return PlayerDeviceControlsType.PAD;
            case 4: return PlayerDeviceControlsType.MOTION_CONTROLLER;
            default: return PlayerDeviceControlsType.UNKNOWN;
        }
    }
}