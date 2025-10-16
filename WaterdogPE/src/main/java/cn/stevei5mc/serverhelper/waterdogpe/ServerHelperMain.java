package cn.stevei5mc.serverhelper.waterdogpe;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.utils.CommonUtils;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.ServerHelperMainCmd;
import cn.stevei5mc.serverhelper.waterdogpe.listener.PlayerListener;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;
import lombok.Getter;

public class ServerHelperMain extends Plugin {
    @Getter
    private static ServerHelperMain instance;
    private final String cmdPrefix = "wd";
    private YamlConfig config;

    @Override
    public void onEnable() {
        instance = this;
        saveConfigResources();
        loadConfig();
        this.getLogger().info(getPluginInfo().replace("\n", " §f| "));
        this.getLogger().warn("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
        this.getLogger().info(BaseInfo.GH_URL);
        this.getProxy().getCommandMap().registerCommand(new ServerHelperMainCmd(cmdPrefix+"serverhelper", "ServerHelper plugin command", BaseInfo.adminMainPermission, CommonUtils.toArray(cmdPrefix+"shr")));
        this.getProxy().getEventManager().subscribe(PlayerChatEvent.class, PlayerListener::onPlayerChat);
    }

    public void saveConfigResources() {
        saveResource("config.yml");
        /*for (String language : BaseInfo.getLanguages()) {
            saveResource(BaseInfo.baseLanguagesFilesPath + language+".yml");
            saveResource(BaseInfo.customLanguagesFilesPath + language+".yml");
        }
        for (String setting : BaseInfo.getSettings()) {
            saveResource("Settings/"+setting+".yml");
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
        return config.getString("message_prefix","§b§lServerHelper §r§7>> ");
    }

    public String getPluginInfo() {
        return BaseInfo.getVersionInfo() + "\n§bPlugin running WaterdogPE";
    }
}