package cn.stevei5mc.serverhelper.nukkit.command.maincommand;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.nukkit.command.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.command.maincommand.sub.PluginVersionCmd;

public class ServerHelperMainCommand extends BaseCommand {

    public ServerHelperMainCommand() {
        super("serverhelper", "ServerHelper plugin command");
        this.setPermission("serverhelper.admin");
        this.addSubCommand(new PluginVersionCmd("version"));
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

    @Override
    public void sendUI(Player player) {
    }
}

