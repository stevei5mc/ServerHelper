package cn.stevei5mc.serverhelper.waterdogpe;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.scheduler.Task;

import java.util.Arrays;
import java.util.List;

public class ServerHelperMain extends Plugin {
    
    private static ServerHelperMain instance;
    private List<String> languages = Arrays.asList(
            "bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"
    );
    private List<String> settings = Arrays.asList("ban","kick","warn","mute");
    
    public static ServerHelperMain getInstance() {
        return instance;
    }
    
    public void onEnable() {
        saveResource("config.yml");
        for (String language : languages) {
            saveResource("language/base/"+language+".yml");
            saveResource("language/custom/"+language+".yml");
        }
        for (String setting : settings) {
            saveResource("Setting/"+setting+".yml");
        }
    }

    public void onDisable() {
        this.getLogger().info("停止运行");
    }
}