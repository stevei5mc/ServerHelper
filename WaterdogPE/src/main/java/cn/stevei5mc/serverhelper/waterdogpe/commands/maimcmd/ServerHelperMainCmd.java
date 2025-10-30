package cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub.ReloadCmd;
import cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub.VersionCmd;
import cn.stevei5mc.serverhelper.waterdogpe.utils.LanguageApi;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18n;
import dev.waterdog.waterdogpe.command.CommandSender;

public class ServerHelperMainCmd extends CommandBase {
    public ServerHelperMainCmd(String name, String description, String permission, String[] aliases) {
        super(name, description, permission, aliases);
        this.addSubCommand(new ReloadCmd("reload"));
        this.addSubCommand(new VersionCmd("version"));
    }

    @Override
    public void sendHelp(CommandSender sender) {
        LanguageApi baseLang = PluginI18n.getBaseLang(sender);
        String cmdName = "/" + getAliasesArray()[0];
        sender.sendMessage(cmdName + " reload " + baseLang.translateString("command-helpMessage-main-reload"));
        sender.sendMessage(cmdName + " version " + baseLang.translateString("command-helpMessage-main-version"));
    }
}