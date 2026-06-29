package cn.stevei5mc.serverhelper.waterdogpe.listener;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.event.defaults.ServerTransferEvent;
import dev.waterdog.waterdogpe.event.defaults.TransferCompleteEvent;

public class ServerListener {

    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    public static void onServerTransfer(ServerTransferEvent event) {
        String sourceServer = event.getSourceServer().getServerName();
        String targetServer = event.getTargetServer().getServerName();
        event.getPlayer().sendMessage("§a" + sourceServer + "  ==>>  " + targetServer);
        main.getLogger().info(String.format("§aPlayer=[%s] Server=[%s]--->>[%s]", event.getPlayer().getName(), sourceServer, targetServer));
    }

    public static void onTransferCompleteEvent(TransferCompleteEvent event) {

    }
}