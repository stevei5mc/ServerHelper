package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

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