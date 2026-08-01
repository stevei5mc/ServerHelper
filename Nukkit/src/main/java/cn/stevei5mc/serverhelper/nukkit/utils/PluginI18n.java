package cn.stevei5mc.serverhelper.nukkit.utils;

import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.ResourcesFilesInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;

import java.util.HashMap;

public class PluginI18n {
    private static final HashMap<String, Language> baseLanguagesMap = new HashMap<>();
    private static final HashMap<String, Language> customLanguagesMap = new HashMap<>();
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
            Config baseLangFile = new Config(Config.YAML);
            baseLangFile.load(main.getResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_BASE.getJarPath() + languageName + ".yml"));
            baseLanguagesMap.put(languageName, new Language(baseLangFile));
            Config customLangFile = new Config(Config.YAML);
            customLangFile.load(main.getResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_CUSTOM.getJarPath()) + languageName + ".yml");
            customLanguagesMap.put(languageName,new Language(customLangFile));
        }
    }

    public static Language getBaseLang() {
        return getBaseLang(null);
    }

    public static Language getBaseLang(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerLanguage = player.getLoginChainData().getLanguageCode();
            if (baseLanguagesMap.containsKey(playerLanguage)) {
                return baseLanguagesMap.get(playerLanguage);
            }
        }
        return baseLanguagesMap.get(defaultLanguage);
    }

    public static Language getPrivateLang() {
        return getPrivateLang(null);
    }

    public static Language getPrivateLang(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerLanguage = player.getLoginChainData().getLanguageCode();
            if (customLanguagesMap.containsKey(playerLanguage)) {
                return customLanguagesMap.get(playerLanguage);
            }
        }
        return customLanguagesMap.get(defaultLanguage);
    }
}