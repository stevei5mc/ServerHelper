package cn.stevei5mc.serverhelper.waterdogpe.utils;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;

import java.util.HashMap;

public class PluginI18n {
    private static final HashMap<String, LanguageApi> baseLanguagesMap = new HashMap<>();
    private static final HashMap<String, LanguageApi> privateLanguagesMap = new HashMap<>();
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
            Configuration baseLangFile = new YamlConfig(main.getDataFolder() + "/"+BaseInfo.baseLanguagesFilesPath + languageName + ".yml");
            baseLangFile.load(main.getResourceFile(BaseInfo.baseLanguagesFilesPath + languageName + ".yml"));
            baseLanguagesMap.put(languageName, new LanguageApi(baseLangFile));
            Configuration privateLangFile = new YamlConfig(main.getDataFolder() + "/"+BaseInfo.privateLanguagesFilesPath + languageName + ".yml");
            privateLangFile.load(main.getResourceFile(BaseInfo.privateLanguagesFilesPath + languageName + ".yml"));
            privateLanguagesMap.put(languageName, new LanguageApi(privateLangFile));
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
            if (privateLanguagesMap.containsKey(playerLanguage)) {
                return privateLanguagesMap.get(playerLanguage);
            }
        }
        return privateLanguagesMap.get(defaultLanguage);
    }
}