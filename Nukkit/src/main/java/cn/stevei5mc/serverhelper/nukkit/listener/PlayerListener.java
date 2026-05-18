package cn.stevei5mc.serverhelper.nukkit.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
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
                        player.sendMessage(PluginI18n.getBaseLang(player).translateString("command-tipMessage-isBanCommandList",message));
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
                    if (message.startsWith(cmd.toLowerCase().trim())) {
                        message = cmd + " ***";
                    }
                }
            }
            main.getServer().getLogger().info(player.getName() + ": §c" + message);
        }
    }
}