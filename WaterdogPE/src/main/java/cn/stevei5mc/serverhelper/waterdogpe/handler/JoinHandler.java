package cn.stevei5mc.serverhelper.waterdogpe.handler;

import dev.waterdog.waterdogpe.network.connection.handler.IJoinHandler;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class JoinHandler implements IJoinHandler {
    @Override
    public ServerInfo determineServer(ProxiedPlayer proxiedPlayer) {
        return null;
    }
}
