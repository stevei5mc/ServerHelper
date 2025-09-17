package cn.stevei5mc.serverhelper.waterdogpe.listener;

import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class PlayerListener {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    public static void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (event.getPlayer().hasPermission(BaseInfo.staffChatPermission) && message.startsWith(main.getConfig().getString("staffChat.sendPrefix", "!staff"))) {
            event.setCancelled(true);
            String sendMessage = main.getConfig().getString("staffChat.message").replace("%player%", event.getPlayer().getName()).replace("%message%",message.replace("!staff",""));
            main.getLogger().info(sendMessage);
            for (ProxiedPlayer player : main.getProxy().getPlayers().values()) {
                player.sendMessage(sendMessage);
            }
        }
    }
}
