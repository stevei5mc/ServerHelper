package cn.stevei5mc.serverhelper.nukkit.commands.maincommand.sub;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.utils.CommonUtils;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseSubCommand;

public class PluginVersionCmd extends BaseSubCommand {
    public PluginVersionCmd(String name){
        super(name);
    }

    @Override
    public boolean canUser(CommandSender sender) {
        return sender.hasPermission(BaseInfo.adminMainPermission);
    }

    @Override
    public String[] getAliases() {
        return CommonUtils.toArray("ver");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§b=== ServerHelper VERSION info ===");
        sender.sendMessage(main.getPluginInfo());
        sender.sendMessage("§b==================================");
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[0];
    }
}