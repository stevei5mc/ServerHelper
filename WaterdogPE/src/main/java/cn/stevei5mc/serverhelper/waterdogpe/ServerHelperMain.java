package cn.stevei5mc.serverhelper.waterdogpe;

import cn.stevei5mc.serverhelper.common.base.BaseInfo;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;

import java.util.Arrays;
import java.util.List;

public class ServerHelperMain extends Plugin {

    private static ServerHelperMain instance;

    private YamlConfig config;
    
    public static ServerHelperMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().warn("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
        this.getLogger().info("§f[§aGITHUB§f] §bhttps://github.com/stevei5mc/ServerHelper");
//        saveConfigResources();
//        loadConfig();
        this.getLogger().info(BaseInfo.version);
        this.getLogger().info(BaseInfo.commitId);
        this.getLogger().info(BaseInfo.branch);
    }

    public void saveConfigResources() {
        saveResource("config.yml");
        for (String language : BaseInfo.getLanguages()) {
            saveResource(BaseInfo.baseLanguagesFilesPath + language+".yml");
            saveResource(BaseInfo.customLanguagesFilesPath + language+".yml");
        }
        for (String setting : BaseInfo.getSettings()) {
            saveResource("Settings/"+setting+".yml");
        }
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