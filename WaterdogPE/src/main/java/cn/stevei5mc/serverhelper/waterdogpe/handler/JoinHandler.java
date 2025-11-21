package cn.stevei5mc.serverhelper.waterdogpe.handler;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.network.connection.handler.IJoinHandler;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.List;
import java.util.Random;

public class JoinHandler implements IJoinHandler {
    @Override
    public ServerInfo determineServer(ProxiedPlayer proxiedPlayer) {
        ServerHelperMain main = ServerHelperMain.getInstance();
        List<String> servers = main.getProxy().getConfiguration().getPriorities();
        return main.getProxy().getServerInfo(servers.get(new Random().nextInt(servers.size())));
    }
}