package cn.stevei5mc.serverhelper.waterdogpe.listener;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.event.defaults.DispatchCommandEvent;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class PlayerListener {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    public static void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage().trim();
        String sendPrefix = main.getConfig().getString("staffChat.sendPrefix", "!staff");
        if (event.getPlayer().hasPermission(BaseInfo.staffChatPermission) && message.startsWith(sendPrefix)) {
            event.setCancelled(true);
            String sendMessage = main.getConfig().getString("staffChat.message").replace("%player%", event.getPlayer().getName()).replace("%message%",message.replace(sendPrefix,""));
            main.getLogger().info(sendMessage);
            for (ProxiedPlayer player : main.getProxy().getPlayers().values()) {
                if (player.hasPermission(BaseInfo.staffChatPermission)) {
                    player.sendMessage(sendMessage);
                }
            }
        }
    }

    public static void onDispatchCommand(DispatchCommandEvent event) {
        String cmd = event.getCommand().trim();
        if (event.getSender().isPlayer()) {
            main.getLogger().info(event.getSender().getName() + ": §c/" + cmd);
        }
    }
}
