package cn.stevei5mc.serverhelper.nukkit.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;

import java.util.ArrayList;

public class PlayerListener implements Listener {
    private final ServerHelperMain main = ServerHelperMain.getInstance();

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        ArrayList<String> playerCommands = new ArrayList<>(main.getBanCommands().getStringList("ban-commands"));
        if (!playerCommands.isEmpty() && main.getBanCommands().getBoolean("enable",false)) {
            for (String cmd : playerCommands) {
                String[] cmd2 = cmd.split("&");
                String permission = "serverhelper.admin.unban.commands";
                if (cmd2.length > 1) {
                    permission = cmd2[1];
                }
                if (message.startsWith(cmd2[0]) && !player.hasPermission(permission)) {
                    player.sendMessage("§c你没有权限执行该命令，请确认后再试！");
                    event.setCancelled(true);
                    break;
                }
            }
        }
        if (!event.isCancelled()) {
            main.getServer().getLogger().warning(player.getName() + ": §c" + message);
        }
    }
}