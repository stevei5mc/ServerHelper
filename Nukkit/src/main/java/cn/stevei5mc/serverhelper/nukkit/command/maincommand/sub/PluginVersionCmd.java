package cn.stevei5mc.serverhelper.nukkit.command.maincommand.sub;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.stevei5mc.serverhelper.nukkit.utils.GitVersionUtil;
import cn.stevei5mc.serverhelper.nukkit.command.base.BaseSubCommand;

public class PluginVersionCmd extends BaseSubCommand {
    public PluginVersionCmd(String name){
        super(name);
    }

    @Override
    public boolean canUser(CommandSender sender) {
        return sender.hasPermission("serverhelper.admin");
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§b=== ServerHelper version info ===");
        sender.sendMessage(GitVersionUtil.getVersion());
        sender.sendMessage(GitVersionUtil.getCommitId());
        sender.sendMessage(GitVersionUtil.getBranch());
        sender.sendMessage("§b==================================");
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[0];
    }
}
