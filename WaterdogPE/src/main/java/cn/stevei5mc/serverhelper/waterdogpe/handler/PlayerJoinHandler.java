package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import dev.waterdog.waterdogpe.network.connection.handler.IJoinHandler;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.types.TextContainer;

public class PlayerJoinHandler implements IJoinHandler {

    @Override
    public ServerInfo determineServer(ProxiedPlayer player) {
        ServerInfo serverInfo = LobbyServersInfo.findServer(player);
        if (serverInfo == null) {
            player.disconnect(new TextContainer("§c无法找到可用服务器，请稍后尝试重进！"));
        }
        return serverInfo;
    }
}