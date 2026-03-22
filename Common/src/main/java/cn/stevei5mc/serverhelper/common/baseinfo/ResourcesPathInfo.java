package cn.stevei5mc.serverhelper.common.baseinfo;

import lombok.Getter;

public enum ResourcesPathInfo {
    SETTINGS_FILES("Settings/"),

    LANGUAGES_BASE_FILES("languages/base/"),
    LANGUAGES_PRIVATE_FILES("languages/private/");

    @Getter
    private final String resourcesPath;
    @Getter
    private final String dataPath;

    ResourcesPathInfo(String path) {
        this.resourcesPath = path;
        this.dataPath = "/" + path;
    }
}