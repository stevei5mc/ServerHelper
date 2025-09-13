package cn.stevei5mc.serverhelper.nukkit.utils;

import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;

import java.util.HashMap;

public class PluginI18n {
    private static final HashMap<String, Language> baseLanguagesMap = new HashMap<>();
    private static final HashMap<String, Language> privateLanguagesMap = new HashMap<>();
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
            baseLangFile.load(main.getResource(BaseInfo.baseLanguagesFilesPath + languageName + ".yml"));
            baseLanguagesMap.put(languageName, new Language(baseLangFile));
            Config privateLangFile = new Config(Config.YAML);
            privateLangFile.load(main.getResource(BaseInfo.privateLanguagesFilesPath + languageName + ".yml"));
            privateLanguagesMap.put(languageName,new Language(privateLangFile));
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
            if (privateLanguagesMap.containsKey(playerLanguage)) {
                return privateLanguagesMap.get(playerLanguage);
            }
        }
        return privateLanguagesMap.get(defaultLanguage);
    }
}