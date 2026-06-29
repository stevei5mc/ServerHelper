package cn.stevei5mc.serverhelper.waterdogpe;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.waterdogpe.commands.LobbyCmd;
import cn.stevei5mc.serverhelper.waterdogpe.commands.StaffChatCmd;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.ServerHelperMainCmd;
import cn.stevei5mc.serverhelper.waterdogpe.handler.PlayerJoinHandler;
import cn.stevei5mc.serverhelper.waterdogpe.handler.PlayerReconnectHandler;
import cn.stevei5mc.serverhelper.waterdogpe.listener.PlayerListener;
import cn.stevei5mc.serverhelper.waterdogpe.listener.ServerListener;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.event.Event;
import dev.waterdog.waterdogpe.event.defaults.DispatchCommandEvent;
import dev.waterdog.waterdogpe.event.defaults.ServerTransferEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;
import lombok.Getter;

import java.util.function.Consumer;

public class ServerHelperMain extends Plugin {
    @Getter
    private static ServerHelperMain instance;
    @Getter
    private YamlConfig config;
    @Getter
    private YamlConfig privateConfig;

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
        if (this.privateConfig.getBoolean("lobby-server.enable-lobby-cmd", true)) {
            this.regCmd(new LobbyCmd("lobby", "lobby cmd", PermissionsInfo.PLAYER_LOBBY.getPermission(), "hub"));
        }
        this.regEventListener(DispatchCommandEvent.class, PlayerListener::onDispatchCommand);
        this.regEventListener(ServerTransferEvent.class, ServerListener::onServerTransfer);
        LobbyServersInfo.loadLobbyServers();
        setHandler();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("已停止运行，感谢你的使用");
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
        privateConfig = new YamlConfig(this.getDataFolder()+"/wdpe-private.yml");
    }

    public String getMessagePrefix() {
        return config.getString("message_prefix","§b§lServerHelper §r§7>> ");
    }

    public String getPluginInfo() {
        return BaseInfo.getVersionInfo() + "\n§bPlugin running WaterdogPE";
    }

    private void regCmd(Command command) {
        this.getProxy().getCommandMap().registerCommand(command);
    }

    private <T extends Event> void regEventListener(Class<T> event, Consumer<T> handler) {
        this.getProxy().getEventManager().subscribe(event, handler);
    }

    private void setHandler() {
        if (privateConfig.getBoolean("handler.enable.join", true)) {
            this.getProxy().setJoinHandler(new PlayerJoinHandler());
        }
        if (privateConfig.getBoolean("handler.enable.reconnect", true)) {
            this.getProxy().setReconnectHandler(new PlayerReconnectHandler());
        }
    }
}