package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import cn.stevei5mc.serverhelper.waterdogpe.utils.LanguageApi;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18n;
import dev.waterdog.waterdogpe.network.connection.handler.IReconnectHandler;
import dev.waterdog.waterdogpe.network.connection.handler.ReconnectReason;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.types.TextContainer;

public class PlayerReconnectHandler implements IReconnectHandler {

    @Override
    public ServerInfo getFallbackServer(ProxiedPlayer player, ServerInfo oldServer, ReconnectReason reason, String kickMessage) {
        LanguageApi lang = PluginI18n.getBaseLang(player);
        String message = "player=[ " + player.getName() + " ] oldServer=[ " + oldServer.getServerName() + " ] reason=[ " + reason.getName() + " ] kickMessage=[ " + kickMessage + " ]";
        ServerHelperMain.getInstance().getLogger().info(message);
        if (reason.equals(ReconnectReason.TIMEOUT) || reason.equals(ReconnectReason.TRANSFER_FAILED)) { // 只有这两种与子服断开链接的原因，才会安排自动重连服务器
            ServerInfo serverInfo = LobbyServersInfo.findServer(player, oldServer);
            if (serverInfo == null) {
                player.disconnect(new TextContainer(lang.translateString("serverTransfer-lobbyServer-message-noAvailable")));
            }
            return serverInfo;
        }
        player.disconnect(new TextContainer(reason.equals(ReconnectReason.SERVER_KICK) ? kickMessage : (reason.equals(ReconnectReason.EXCEPTION) ?
                lang.translateString("serverDisconnect-message-exception") : lang.translateString("serverDisconnect-message-unknown"))));
        return null;
    }
}