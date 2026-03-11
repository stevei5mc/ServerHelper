package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum PermissionsInfo {

    ADMIN_MAIN("serverhelper.admin"),
    ADMIN_RELOAD("serverhelper.admin.reload"),

    STAFF_CHAT("serverhelper.staff.chat"),

    BAN_CMD_BYPASS("serverhelper.admin.unban.commands");


    @Getter
    private final String permission;

    PermissionsInfo(String permission) {
        this.permission = permission;
    }
}