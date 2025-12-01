package cn.stevei5mc.serverhelper.waterdogpe.listener;

import dev.waterdog.waterdogpe.event.defaults.ServerTransferEvent;

public class ServerListener {


    public static void onServerTransfer(ServerTransferEvent event) {
        String sourceServer = event.getSourceServer().getServerName();
        String targetServer = event.getTargetServer().getServerName();
        event.getPlayer().sendMessage("§a" + sourceServer + "  ==>>  " + targetServer);
    }
}