package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum PermissionsInfo {

    ADMIN_MAIN(Type.ADMIN, null),
    ADMIN_RELOAD(Type.ADMIN, "reload"),

    STAFF_CHAT(Type.STAFF, "chat"),

    PLAYER_LOBBY(Type.USER, "lobby"),

    BAN_CMD_BYPASS(Type.OTHER, "banCommands.bypass");


    private static final String ROOT = "serverhelper.";
    @Getter
    private final Type type;
    @Getter
    private final String permission;


    PermissionsInfo(Type type, String node) {
        this.type = type;
        if (type.equals(Type.NONE)) {
            this.permission = ROOT + (node != null ? node : "unknown");
        }else {
            this.permission = ROOT + type.getNode() + (node != null ? "." + node : "");
        }
    }

    public enum Type {

        ADMIN("admin"),
        STAFF("staff"),
        USER("user"),
        OTHER("other"),
        NONE("none", "");

        @Getter
        private final String name;
        @Getter
        private final String node;

        Type(String name) {
            this.name = name;
            this.node = name;
        }

        Type(String name, String node) {
            this.name = name;
            this.node = node;
        }
    }
}