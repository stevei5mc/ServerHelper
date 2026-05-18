package cn.stevei5mc.serverhelper.nukkit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseCommand;

public class StaffChatCmd extends BaseCommand {
    public StaffChatCmd(String name, String description) {
        super(name, description);
        this.setPermission(PermissionsInfo.STAFF_CHAT.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender.isPlayer()) {
            Player player = (Player) sender;
            String message = main.getConfig().getString("staffChat.message").replace("%player%", player.getName()).replace("%message%", args[0]);
            for (Player staffPlayer : main.getServer().getOnlinePlayers().values()) {
                if (staffPlayer.hasPermission(this.getPermission())) {
                    staffPlayer.sendMessage(message);
                }
            }
        }
        return true;
    }

    @Override
    public void sendHelp(CommandSender sender) {}

    @Override
    public void sendUI(Player player) {}
}