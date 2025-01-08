package cn.stevei5mc.serverhelper.nukkit.command.admin;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.nukkit.command.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.gui.MainGui;

public class AdminCommand extends BaseCommand {
    public AdminCommand(String name) {
        super(name,"ServerHelper admin command");
        this.setPermission("serverhelper.admin");
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

    @Override
    public void sendUI(Player player) {
        MainGui.sendMain(player);
    }
}
