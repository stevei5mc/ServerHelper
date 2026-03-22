package cn.stevei5mc.serverhelper.waterdogpe.listener;

import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.event.defaults.DispatchCommandEvent;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    public static void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage().trim();
        String sendPrefix = main.getConfig().getString("staffChat.sendPrefix", "!staff");
        if (event.getPlayer().hasPermission(PermissionsInfo.STAFF_CHAT.getPermission()) && message.startsWith(sendPrefix)) {
            event.setCancelled(true);
            String sendMessage = main.getConfig().getString("staffChat.message").replace("%player%", event.getPlayer().getName()).replace("%message%",message.replace(sendPrefix,""));
            main.getLogger().info(sendMessage);
            for (ProxiedPlayer player : main.getProxy().getPlayers().values()) {
                if (player.hasPermission(PermissionsInfo.STAFF_CHAT.getPermission())) {
                    player.sendMessage(sendMessage);
                }
            }
        }
    }

    public static void onDispatchCommand(DispatchCommandEvent event) {
        if (main.getConfig().getBoolean("commands.usageLog.enable", true) && event.getSender().isPlayer()) {
            StringBuilder cmdBuilder = new StringBuilder();
            cmdBuilder.append(event.getCommand());
            if (event.getArgs().length >= 1) {
                for (String param : event.getArgs()) {
                    cmdBuilder.append(" ").append(param);
                }
            }
            String cmd = cmdBuilder.toString().toLowerCase().trim();
            String cmd2Log = cmd;
            List<String> secretsCmd = main.getConfig().getStringList("commands.usageLog.secretsList", new ArrayList<>());
            if (!secretsCmd.isEmpty()) {
                for (String secretCmd: secretsCmd) {
                    if (cmd.startsWith(secretCmd.toLowerCase().trim())) {
                        cmd2Log = secretCmd.toLowerCase().trim() + " ***";
                    }
                }
            }
            main.getLogger().info(event.getSender().getName() + ": /" + cmd2Log);
        }
    }
}