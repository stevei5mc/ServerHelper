package cn.stevei5mc.serverhelper.nukkit.commands.admin;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.form.MainForm;

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
        MainForm.mainMenu(player);
    }
}
