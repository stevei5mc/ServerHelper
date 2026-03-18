package cn.stevei5mc.serverhelper.common.utils.player;

import lombok.Getter;

public enum PlayerDeviceUiType {
    CLASSIC("Classic"),
    POCKET("Pocket"),
    UNKNOWN("Unknown");

    @Getter
    private final String name;

    PlayerDeviceUiType(String name) {
        this.name = name;
    }
}