package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import dev.waterdog.waterdogpe.network.connection.handler.IReconnectHandler;
import dev.waterdog.waterdogpe.network.connection.handler.ReconnectReason;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.types.TextContainer;

public class PlayerReconnectHandler implements IReconnectHandler {

    @Override
    public ServerInfo getFallbackServer(ProxiedPlayer player, ServerInfo oldServer, ReconnectReason reason, String kickMessage) {
        String message = "player=[ " + player.getName() + " ] oldServer=[ " + oldServer.getServerName() + " ] reason=[ " + reason.getName() + " ] kickMessage=[ " + kickMessage + " ]";
        ServerHelperMain.getInstance().getLogger().info(message);
        if (reason.equals(ReconnectReason.TIMEOUT) || reason.equals(ReconnectReason.TRANSFER_FAILED)) { // 只有这两种与子服断开链接的原因，才会安排自动重连服务器
            ServerInfo serverInfo = LobbyServersInfo.findServer(player, oldServer);
            if (serverInfo == null) {
                player.disconnect(new TextContainer("§c无法找到可用服务器，请稍后尝试重进！"));
            }
            return serverInfo;
        }
        player.disconnect(new TextContainer(reason.equals(ReconnectReason.SERVER_KICK) ? kickMessage : (reason.equals(ReconnectReason.EXCEPTION) ?
                "§c发生了异常情况导致与服务器断开链接" : "§c发生了未知原因导致断开链接" ) + "，请自行重新加入服务器 \n" + message));
        return null;
    }
}