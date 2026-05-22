package cn.stevei5mc.serverhelper.waterdogpe.commands;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class StaffChatCmd extends CommandBase {
    public StaffChatCmd(String name, String description, String permission) {
        super(name, description, permission);
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (sender.isPlayer()) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            String message = main.getConfig().getString("staffChat.message").replace("%player%", player.getName()).replace("%message%", args[0]);
            for (ProxiedPlayer staffPlayer : main.getProxy().getPlayerManager().getPlayers().values()) {
                if (staffPlayer.hasPermission(this.getPermission())) {
                    staffPlayer.sendMessage(message);
                }
            }
        }
        return true;
    }

    @Override
    public void sendHelp(CommandSender sender) {}
}