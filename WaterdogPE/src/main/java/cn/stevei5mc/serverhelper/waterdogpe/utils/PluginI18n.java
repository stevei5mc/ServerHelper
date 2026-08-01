package cn.stevei5mc.serverhelper.waterdogpe.utils;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.ResourcesFilesInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;

import java.util.HashMap;

public class PluginI18n {
    private static final HashMap<String, LanguageApi> baseLanguagesMap = new HashMap<>();
    private static final HashMap<String, LanguageApi> customLanguagesMap = new HashMap<>();
    private static final ServerHelperMain main = ServerHelperMain.getInstance();
    private static String defaultLanguage;

    public static void loadLanguages() {
        defaultLanguage = main.getConfig().getString("default_language", BaseInfo.defaultLanguage);
        if (!BaseInfo.getLanguages().contains(defaultLanguage)) {
            main.getLogger().error("Language" + defaultLanguage + "Not supported, will load " + BaseInfo.defaultLanguage);
            defaultLanguage = BaseInfo.defaultLanguage;
        }
        main.getLogger().info("Default language " + defaultLanguage);
        for (String languageName : BaseInfo.getLanguages()) {
            Configuration baseLangFile = new YamlConfig(main.getDataFolder() + ResourcesFilesInfo.PathsInfo.LANGUAGES_BASE.getDataPath() + languageName + ".yml");
            baseLangFile.load(main.getResourceFile(ResourcesFilesInfo.PathsInfo.LANGUAGES_BASE.getJarPath() + languageName + ".yml"));
            baseLanguagesMap.put(languageName, new LanguageApi(baseLangFile));
            Configuration privateLangFile = new YamlConfig(main.getDataFolder() + ResourcesFilesInfo.PathsInfo.LANGUAGES_CUSTOM.getDataPath() + languageName + ".yml");
            privateLangFile.load(main.getResourceFile(ResourcesFilesInfo.PathsInfo.LANGUAGES_CUSTOM.getJarPath() + languageName + ".yml"));
            customLanguagesMap.put(languageName, new LanguageApi(privateLangFile));
        }
    }

    public static LanguageApi getBaseLang() {
        return getBaseLang(null);
    }

    public static LanguageApi getBaseLang(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            String playerLanguage = String.valueOf(player.getLoginData().getClientData().get("LanguageCode")).replace("\"","");
            if (baseLanguagesMap.containsKey(playerLanguage)) {
                return baseLanguagesMap.get(playerLanguage);
            }
        }
        return baseLanguagesMap.get(defaultLanguage);
    }

    public static LanguageApi getPrivateLang() {
        return getPrivateLang(null);
    }

    public static LanguageApi getPrivateLang(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            String playerLanguage = String.valueOf(player.getLoginData().getClientData().get("LanguageCode")).replace("\"","");
            if (customLanguagesMap.containsKey(playerLanguage)) {
                return customLanguagesMap.get(playerLanguage);
            }
        }
        return customLanguagesMap.get(defaultLanguage);
    }
}