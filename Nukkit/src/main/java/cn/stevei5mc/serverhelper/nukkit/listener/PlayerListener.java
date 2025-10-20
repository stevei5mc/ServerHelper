package cn.stevei5mc.serverhelper.nukkit.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class PlayerListener implements Listener {
    private final ServerHelperMain main = ServerHelperMain.getInstance();

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().trim().toLowerCase();
        ArrayList<String> banCommands = new ArrayList<>(main.getBanCommands().getStringList("ban-commands"));
        if (!banCommands.isEmpty() && main.getBanCommands().getBoolean("enable",false)) {
            for (String cmd : banCommands) {
                String[] cmd2 = cmd.split("&");
                String permission = BaseInfo.unbanCommandPermission;
                if (message.startsWith(cmd2[0].trim().toLowerCase())) {
                    if (cmd2.length >= 2 && !cmd2[1].isEmpty()) {
                        permission = cmd2[1];
                    }
                    boolean isBanCmdWorld = cmd2.length < 3; // 在没有写禁用命令的世界时 = true
                    if (cmd2.length == 3) {
                        LinkedList<String> worldBanCmdList = new LinkedList<>(Arrays.asList(cmd2[2].split("%")));
                        isBanCmdWorld = worldBanCmdList.contains(player.getLevel().getFolderName());
                    }
                    if (!player.hasPermission(permission) && isBanCmdWorld) {
                        player.sendMessage("§c你没有权限执行该命令，请确认后再试！");
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
        if (main.getConfig().getBoolean("commands.usageLog.enable", true) && !event.isCancelled()) {
            ArrayList<String> secretsCmd = new ArrayList<>(main.getConfig().getStringList("commands.usageLog.secretsList"));
            if (!secretsCmd.isEmpty()) {
                for (String secrets : secretsCmd) {
                    if (message.startsWith(secrets)) {
                        message = secrets + "***";
                        break;
                    }
                }
            }
            main.getServer().getLogger().info(player.getName() + ": §c" + message);
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage().trim();
        String sendPrefix = main.getConfig().getString("staffChat.sendPrefix", "!staff");
        if (!main.getPrivateConfig().getBoolean("waterdogPE-mode",false) && event.getPlayer().hasPermission(BaseInfo.staffChatPermission) && message.startsWith(sendPrefix)) {
            event.setCancelled(true);
            String sendMessage = main.getConfig().getString("staffChat.message").replace("%player%", event.getPlayer().getName()).replace("%message%",message.replace(sendPrefix,""));
            main.getLogger().info(sendMessage);
            for (Player player : main.getServer().getOnlinePlayers().values()) {
                if (player.hasPermission(BaseInfo.staffChatPermission)) {
                    player.sendMessage(sendMessage);
                }
            }
        }
    }
}