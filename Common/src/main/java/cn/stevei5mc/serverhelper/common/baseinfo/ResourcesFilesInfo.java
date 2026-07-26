package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum ResourcesFilesInfo {

    DEFAULT_CONFIG("config",1, PathsInfo.DEFAULT),
    BAN_COMMANDS_CONFIG("banCommands", PathsInfo.SETTINGS);

    @Getter
    private final String name;
    @Getter
    private final int version;
    @Getter
    private final PathsInfo pathInfo;
    @Getter
    private final String jarPath;
    @Getter
    private final String dataPath;

    ResourcesFilesInfo(String name, PathsInfo pathInfo) {
        this.name = name + ".yml";
        this.version = 0;
        this.pathInfo = pathInfo;
        this.jarPath = pathInfo.getJarPath() + this.name;
        this.dataPath = pathInfo.getDataPath() + this.name;
    }

    ResourcesFilesInfo(String name, int version, PathsInfo pathInfo) {
        this.name = name + ".yml";
        this.version = version;
        this.pathInfo = pathInfo;
        this.jarPath = pathInfo.getJarPath() + this.name;
        this.dataPath = pathInfo.getDataPath() + this.name;
    }

    public enum PathsInfo {
        DEFAULT(""),

        SETTINGS("Settings/"),

        LANGUAGES_BASE("languages/base/"),
        LANGUAGES_PRIVATE("languages/private/");

        @Getter
        private final String jarPath;
        @Getter
        private final String dataPath;

        PathsInfo(String path) {
            this.jarPath = path.endsWith("/") ? path : path + "/";
            this.dataPath = "/" + this.jarPath;
        }
    }
}