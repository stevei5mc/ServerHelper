package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum ResourcesFilesInfo {

    DEFAULT_CONFIG("config",1, ResourcesPathInfo.DEFAULT),
    BAN_COMMANDS("banCommands", ResourcesPathInfo.SETTINGS);

    @Getter
    private final String name;
    @Getter
    private final int version;
    @Getter
    private final ResourcesPathInfo pathInfo;
    @Getter
    private final String jarPath;
    @Getter
    private final String dataPath;

    ResourcesFilesInfo(String name, ResourcesPathInfo pathInfo) {
        this.name = name + ".yml";
        this.version = 0;
        this.pathInfo = pathInfo;
        this.jarPath = pathInfo.getResourcesPath() + this.name;
        this.dataPath = pathInfo.getDataPath() + this.name;
    }

    ResourcesFilesInfo(String name, int version, ResourcesPathInfo pathInfo) {
        this.name = name + ".yml";
        this.version = version;
        this.pathInfo = pathInfo;
        this.jarPath = pathInfo.getResourcesPath() + this.name;
        this.dataPath = pathInfo.getDataPath() + this.name;
    }

    public enum ResourcesPathInfo {
        DEFAULT(""),

        SETTINGS("Settings/"),

        LANGUAGES_BASE("languages/base/"),
        LANGUAGES_PRIVATE("languages/private/");

        @Getter
        private final String resourcesPath;
        @Getter
        private final String dataPath;

        ResourcesPathInfo(String path) {
            this.resourcesPath = path;
            this.dataPath = "/" + path;
        }
    }
}