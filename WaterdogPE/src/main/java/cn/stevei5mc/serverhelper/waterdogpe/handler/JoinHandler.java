package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import dev.waterdog.waterdogpe.network.connection.handler.IJoinHandler;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.Random;

public class JoinHandler implements IJoinHandler {
    @Override
    public ServerInfo determineServer(ProxiedPlayer proxiedPlayer) {
        return LobbyServersInfo.getLobbyServerList().get(new Random().nextInt(LobbyServersInfo.getLobbyServerList().size()));
    }
}