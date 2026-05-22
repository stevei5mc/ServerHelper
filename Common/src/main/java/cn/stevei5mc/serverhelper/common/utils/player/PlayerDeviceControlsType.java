package cn.stevei5mc.serverhelper.common.utils.player;

import lombok.Getter;

public enum PlayerDeviceControlsType {
    KEYBOARD("Keyboard"),
    TOUCH("Touch"),
    PAD("Pad"),
    MOTION_CONTROLLER("Motion controller"),
    UNKNOWN("Unknown");

    @Getter
    private final String name;

    PlayerDeviceControlsType(String name) {
        this.name = name;
    }
}