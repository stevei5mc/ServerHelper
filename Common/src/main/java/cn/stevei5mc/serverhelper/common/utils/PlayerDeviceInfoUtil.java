package cn.stevei5mc.serverhelper.common.utils;

public class PlayerDeviceInfoUtil {
    public static String getDeviceOS(int os) {
        switch (os) {
            case 1: return"Android";
            case 2: return "iOS";
            case 3: return "macOS";
            case 4: return "Fire OS";
            case 5: return "Gear VR";
            case 6: return "HoloLens";
            case 7: return "Windows 10";
            case 8: return "Windows";
            case 9: return "Dedicated";
            case 10: return "tvOS";
            case 11: return "PlayStation";
            case 12: return "Switch";
            case 13: return "Xbox";
            case 14: return "Windows Phone";
            default: return "§cUnknown Device OS: " + os +"§f";
        }
    }

    public static String getPlayerUi(int ui) {
        switch (ui) {
            case 0: return "classic";
            case 1: return "pocket";
            default: return "§cUnknown UIProfile: "+ui+"§f";
        }
    }

    public static String getDeviceControls(int ctrl) {
        switch (ctrl) {
            case 1: return "Keyboard";
            case 2: return "Touch";
            case 3: return "pad";
            case 4: return "motion controller";
            default: return "§cUnknown controls: "+ctrl+"§f";
        }
    }
}