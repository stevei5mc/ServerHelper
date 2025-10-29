package cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub.ReloadCmd;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub.VersionCmd;
import dev.waterdog.waterdogpe.command.CommandSender;

public class ServerHelperMainCmd extends CommandBase {
    public ServerHelperMainCmd(String name, String description, String permission, String[] aliases) {
        super(name, description, permission, aliases);
        this.addSubCommand(new ReloadCmd("reload"));
        this.addSubCommand(new VersionCmd("version"));
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wdshr [version | reload]");
    }
}