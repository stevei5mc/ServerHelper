package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.network.connection.handler.IReconnectHandler;
import dev.waterdog.waterdogpe.network.connection.handler.ReconnectReason;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class ReconnectHandler implements IReconnectHandler {
    @Override
    public ServerInfo getFallbackServer(ProxiedPlayer player, ServerInfo oldServer, ReconnectReason reason, String kickMessage) {
        ServerHelperMain.getInstance().getLogger().debug("player=[ " + player.getName() + " ] oldServer=[ " + oldServer.getServerName() + " ] reason=[ " + reason.getName() + " ] kickMessage=[ " + kickMessage + " ]");
        return ServerHelperMain.getInstance().getProxy().getServerInfo(ServerHelperMain.getInstance().getProxy().getConfiguration().getPriorities().get(1));
    }
}