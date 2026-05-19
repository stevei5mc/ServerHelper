package cn.stevei5mc.serverhelper.waterdogpe;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.ResourcesFilesInfo;
import cn.stevei5mc.serverhelper.waterdogpe.commands.StaffChatCmd;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.ServerHelperMainCmd;
import cn.stevei5mc.serverhelper.waterdogpe.listener.PlayerListener;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.event.Event;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18n;
import dev.waterdog.waterdogpe.event.defaults.DispatchCommandEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;
import lombok.Getter;

import java.util.function.Consumer;

public class ServerHelperMain extends Plugin {
    @Getter
    private static ServerHelperMain instance;
    private YamlConfig config;

    @Override
    public void onEnable() {
        instance = this;
        saveConfigResources();
        loadConfig();
        this.getLogger().info(getPluginInfo().replace("\n", " §f| "));
        this.getLogger().warn("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
        this.getLogger().info(BaseInfo.GH_URL);
        this.regCmd(new ServerHelperMainCmd("serverhelper-wdpe", "ServerHelper plugin command", PermissionsInfo.ADMIN_MAIN.getPermission(), "shr-wdpe"));
        this.regCmd(new StaffChatCmd(config.getString("commands.name.staffChat", "staffchat"), "ServerHelper Staff chat command", PermissionsInfo.STAFF_CHAT.getPermission()));
        this.regEventListener(DispatchCommandEvent.class, PlayerListener::onDispatchCommand);
    }

    public void saveConfigResources() {
        saveResource("config.yml");
        for (String language : BaseInfo.getLanguages()) {
            saveResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_BASE.getJarPath() + language+".yml");
//            saveResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_PRIVATE.getJarPath() + language+".yml");
        }
        /*for (String setting : BaseInfo.getSettings()) {
            saveResource("Settings/"+setting+".yml");
        }*/
    }

    @Override
    public void loadConfig() {
        config = new YamlConfig(this.getDataFolder()+"/config.yml");
        PluginI18n.loadLanguages();
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

    public void regCmd(Command command) {
        this.getProxy().getCommandMap().registerCommand(command);
    }

    public <T extends Event> void regEventListener(Class<T> event, Consumer<T> handler) {
        this.getProxy().getEventManager().subscribe(event, handler);
    }
}