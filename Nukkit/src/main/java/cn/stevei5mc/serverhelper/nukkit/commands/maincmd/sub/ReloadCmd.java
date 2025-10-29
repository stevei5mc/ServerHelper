package cn.stevei5mc.serverhelper.nukkit.commands.maincmd.sub;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseSubCommand;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;

public class ReloadCmd extends BaseSubCommand {
    public ReloadCmd(String name) {
        super(name);
    }

    @Override
    public boolean canUser(CommandSender sender) {
        return sender.hasPermission(BaseInfo.reloadPermission);
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        main.loadConfig();
        sender.sendMessage(main.getMessagePrefix() + PluginI18n.getBaseLang().translateString("message-configReload-success"));
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[0];
    }
}