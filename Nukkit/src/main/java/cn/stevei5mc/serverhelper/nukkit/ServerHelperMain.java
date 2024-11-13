package cn.stevei5mc.serverhelper.nukkit;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.Server;
import cn.nukkit.utils.Config;

import java.util.Arrays;
import java.util.List;

public class ServerHelperMain extends PluginBase {
    
    private static ServerHelperMain instance;
    private List<String> languages = Arrays.asList(
            "bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"
    );
    private List<String> settings = Arrays.asList("ban","kick","warn");
    private Config config;

    public static ServerHelperMain getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();
        for (String language : languages) {
            saveResource("language/base/"+language+".yml");
            saveResource("language/custom/"+language+".yml");
        }
        for (String setting : settings) {
            saveResource("Setting/"+setting+"yml");
        }
    }

    public void onEnable() {
        instance = this;
        Server.getInstance().getScheduler().scheduleDelayedTask(this, () -> {
            this.getLogger().warning("§c警告! §c本插件为免费且开源的一款插件，如果你是付费获取到的那么你就被骗了");
            this.getLogger().info("§a开源链接和使用方法: §bhttps://github.com/stevei5mc/ServerHelper");
        },20);
    }

    public void onDisable() {
        this.getLogger().info("已停止运行，感谢你的使用");
    }

    public Config getConfig() {
        return config;
    }
}