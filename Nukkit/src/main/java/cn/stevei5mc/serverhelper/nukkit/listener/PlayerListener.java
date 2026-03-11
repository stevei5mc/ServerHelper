package cn.stevei5mc.serverhelper.nukkit.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
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
            for (String banCmd : banCommands) {
                String[] banCmdConfig = banCmd.split("&");
                String cmdName = banCmdConfig[0].toLowerCase();
                String cmdPrefix = cmdName.startsWith("/") ? cmdName : "/" + cmdName;
                String permission = PermissionsInfo.BAN_CMD_BYPASS.getPermission();
                if (message.startsWith(cmdPrefix)) {
                    if (banCmdConfig.length >= 2 && !banCmdConfig[1].isEmpty()) {
                        permission = banCmdConfig[1];
                    }
                    boolean isBanCmdWorld = banCmdConfig.length < 3; // 在没有写禁用命令的世界时 = true
                    if (banCmdConfig.length == 3) {
                        LinkedList<String> worldBanCmdList = new LinkedList<>(Arrays.asList(banCmdConfig[2].split("%")));
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
            ArrayList<String> secretsCommands = new ArrayList<>(main.getConfig().getStringList("commands.usageLog.secretsList"));
            if (!secretsCommands.isEmpty()) {
                for (String secrets : secretsCommands) {
                    String cmd = secrets.startsWith("/") ? secrets : "/" + secrets;
                    if (message.startsWith(cmd)) {
                        message = secrets + " ***";
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
        if (!main.getPrivateConfig().getBoolean("waterdogPE-mode",false) && event.getPlayer().hasPermission(PermissionsInfo.STAFF_CHAT.getPermission()) && message.startsWith(sendPrefix)) {
            event.setCancelled(true);
            String sendMessage = main.getConfig().getString("staffChat.message").replace("%player%", event.getPlayer().getName()).replace("%message%",message.replace(sendPrefix,""));
            main.getLogger().info(sendMessage);
            for (Player player : main.getServer().getOnlinePlayers().values()) {
                if (player.hasPermission(PermissionsInfo.STAFF_CHAT.getPermission())) {
                    player.sendMessage(sendMessage);
                }
            }
        }
    }
}