package cn.stevei5mc.serverhelper.nukkit.commands.maincommand;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.common.utils.CommonUtils;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.commands.maincommand.sub.PluginVersionCmd;
import cn.stevei5mc.serverhelper.nukkit.commands.maincommand.sub.ReloadCmd;

public class ServerHelperMainCommand extends BaseCommand {

    public ServerHelperMainCommand() {
        super("serverhelper", "ServerHelper plugin command");
        this.setPermission(BaseInfo.adminMainPermission);
        this.setAliases(CommonUtils.toArray("shr"));
        this.addSubCommand(new PluginVersionCmd("version"));
        this.addSubCommand(new ReloadCmd("reload"));
    }

    @Override
    public void sendHelp(CommandSender sender) {
    }

    @Override
    public void sendUI(Player player) {
    }
}

