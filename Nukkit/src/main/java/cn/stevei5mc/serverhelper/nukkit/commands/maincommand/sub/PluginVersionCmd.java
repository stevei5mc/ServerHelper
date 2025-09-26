package cn.stevei5mc.serverhelper.nukkit.commands.maincommand.sub;

import cn.lanink.gamecore.utils.NukkitTypeUtils;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
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
        return new String[0];
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§b=== ServerHelper VERSION info ===");
        sender.sendMessage(BaseInfo.VERSION);
        sender.sendMessage(BaseInfo.COMMIT_ID);
        sender.sendMessage(BaseInfo.BRANCH);
        sender.sendMessage("§bNukkit type: §a" + NukkitTypeUtils.getNukkitType().name());
        sender.sendMessage("§b==================================");
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[0];
    }
}
