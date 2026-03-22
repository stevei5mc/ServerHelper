package cn.stevei5mc.serverhelper.common.utils.player;

import lombok.Getter;

public enum PlayerDeviceOsType {
    ANDROID("Android"),
    IOS("iOS"),
    MACOS("macOS"),
    FIRE_OS("Fire os"),
    GEAR_VR("Gear VR"),
    HOLOLENS("Hololens"),
    WINDOWS("Windows"),
    WINDOWS_X86("Windows x86"),
    DEDICATED("Dedicated"),
    TV_OS("tvOS"),
    PLAY_STATION("PlayStation"),
    SWITCH("Switch"),
    XBOX("Xbox"),
    WINDOWS_PHONE("Windows phone"),
    LINUX("Linux"),
    UNKNOWN("Unknown");

    @Getter
    private final String name;

    PlayerDeviceOsType(String name) {
        this.name = name;
    }
}