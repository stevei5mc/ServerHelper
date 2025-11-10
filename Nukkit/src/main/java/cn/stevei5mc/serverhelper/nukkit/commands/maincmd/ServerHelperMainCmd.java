package cn.stevei5mc.serverhelper.nukkit.commands.maincmd;

import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.utils.CommonUtils;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.commands.maincmd.sub.VersionCmd;
import cn.stevei5mc.serverhelper.nukkit.commands.maincmd.sub.ReloadCmd;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;

public class ServerHelperMainCmd extends BaseCommand {

    public ServerHelperMainCmd() {
        super("serverhelper", "ServerHelper plugin command");
        this.setPermission(BaseInfo.adminMainPermission);
        this.setAliases(CommonUtils.toArray("shr"));
        this.addSubCommand(new VersionCmd("version"));
        this.addSubCommand(new ReloadCmd("reload"));
    }

    @Override
    public void sendHelp(CommandSender sender) {
        Language baseLang = PluginI18n.getBaseLang(sender);
        String cmdName = "/" + getAliases()[0];
        sender.sendMessage(cmdName + " reload " + baseLang.translateString("command-helpMessage-main-reload"));
        sender.sendMessage(cmdName + " version " + baseLang.translateString("command-helpMessage-main-version"));
    }

    @Override
    public void sendUI(Player player) {
    }
}