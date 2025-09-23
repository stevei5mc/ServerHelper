package cn.stevei5mc.serverhelper.nukkit.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class PlayerListener implements Listener {
    private final ServerHelperMain main = ServerHelperMain.getInstance();

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().trim();
        ArrayList<String> playerCommands = new ArrayList<>(main.getBanCommands().getStringList("ban-commands"));
        if (!playerCommands.isEmpty() && main.getBanCommands().getBoolean("enable",false)) {
            for (String cmd : playerCommands) {
                String[] cmd2 = cmd.split("&");
                String permission = BaseInfo.unbanCommandPermission;
                if (message.equalsIgnoreCase(cmd2[0].trim())) {
                    if (cmd2.length >= 2 && !cmd2[1].isEmpty()) {
                        permission = cmd2[1];
                    }
                    boolean isBanCmdWorld = cmd2.length < 3; // 在没有写禁用命令的世界时 = true
                    if (cmd2.length == 3) {
                        LinkedList<String> worldBanCmdList = new LinkedList<>(Arrays.asList(cmd2[2].split("%")));
                        isBanCmdWorld = worldBanCmdList.contains(player.getLevel().getFolderName());
                    }
                    if (!player.hasPermission(permission) && isBanCmdWorld) {
                        player.sendMessage(PluginI18n.getBaseLang(player).translateString("command-message-isBanCommandList",message));
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
        if (!event.isCancelled()) {
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