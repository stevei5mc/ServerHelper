package cn.stevei5mc.serverhelper.waterdogpe;

import cn.stevei5mc.serverhelper.waterdogpe.utils.GitVersionUtil;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;

import java.util.Arrays;
import java.util.List;

public class ServerHelperMain extends Plugin {

    private static ServerHelperMain instance;
    private final String version = "";
    private final List<String> languages = Arrays.asList(
            "bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"
    );
    private final List<String> settings = Arrays.asList("ban","kick","warn","mute");
    private YamlConfig config;
    
    public static ServerHelperMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().warn("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
        this.getLogger().info("§a开源链接和使用方法: §bhttps://github.com/stevei5mc/ServerHelper");
        saveConfigResources();
        loadConfig();
        this.getLogger().info(GitVersionUtil.getVersion());
        this.getLogger().info(GitVersionUtil.getCommitId());
        this.getLogger().info(GitVersionUtil.getBranch());
    }

    public void saveConfigResources() {
        saveResource("config.yml");
        /*for (String language : languages) {
            saveResource("language/base/"+language+".yml");
            saveResource("language/custom/"+language+".yml");
        }
        for (String setting : settings) {
            saveResource("Setting/"+setting+".yml");
        }*/
    }

    @Override
    public void loadConfig() {
        config = new YamlConfig(this.getDataFolder()+"/config.yml");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("已停止运行，感谢你的使用");
    }

    @Override
    public Configuration getConfig() {
        return config;
    }

    public String getMessagePrefix() {
        return config.getString("message_prefix","§b§ServerHelper §r§7>> ");
    }
}