package cn.stevei5mc.serverhelper.waterdogpe.listener;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.event.defaults.DispatchCommandEvent;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Optional;

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
        if (main.getConfig().getBoolean("commands.usageLog.enable", true) && event.getSender().isPlayer()) {
            String cmd = event.getCommand().trim().toLowerCase();
            ArrayList<String> secretsCmd = Optional.ofNullable(main.getConfig().getStringList("commands.usageLog.secretsList"))
                .map(ArrayList::new).orElse(new ArrayList<>());
            if (!secretsCmd.isEmpty()) {
                for (String secrets : secretsCmd) {
                    if (cmd.startsWith(secrets)) {
                        cmd = secrets + "***";
                    }
                }
            }
            main.getLogger().info(event.getSender().getName() + ": " + cmd);
        }
    }
}
