package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum PermissionsInfo {

    ADMIN_MAIN(Type.ADMIN, null),
    ADMIN_RELOAD(Type.ADMIN, "reload"),

    STAFF_CHAT(Type.STAFF, "chat"),

    BAN_CMD_BYPASS(Type.OTHER, "banCommands.bypass");


    private final String ROOT = "serverhelper.";
    @Getter
    private final String type;
    @Getter
    private final String permission;

    PermissionsInfo(Type type, String node) {
        this.type = type.name;
        if (node == null) {
            this.permission = ROOT + type.getNode();
        }else {
            this.permission = ROOT + type.getNode() + "." +node;
        }
    }

    public enum Type {

        ADMIN("admin"),
        STAFF("staff"),
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