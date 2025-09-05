package cn.stevei5mc.serverhelper.nukkit.utils;

import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;

public class PluginI18n {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();
    private static String defaultLanguage;

    public static void loadLanguage() {
        defaultLanguage = main.getConfig().getString("default_language", BaseInfo.defaultLanguage);
        if (!BaseInfo.getLanguages().contains(defaultLanguage)) {
            main.getLogger().error("Language" + defaultLanguage + "Not supported, will load " + BaseInfo.defaultLanguage);
            defaultLanguage = BaseInfo.defaultLanguage;
        }
        main.getLogger().info("Default language " + defaultLanguage);
    }
}