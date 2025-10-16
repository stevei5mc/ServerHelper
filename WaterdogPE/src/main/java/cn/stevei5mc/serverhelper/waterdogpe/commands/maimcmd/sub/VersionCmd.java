package cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.utils.CommonUtils;
import cn.stevei5mc.serverhelper.waterdogpe.commands.base.SubCommandBase;
import dev.waterdog.waterdogpe.command.CommandSender;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public class VersionCmd extends SubCommandBase {
    public VersionCmd(String name) {
        super(name);
    }

    @Override
    public String[] getAliases() {
        return CommonUtils.toArray("ver");
    }

    @Override
    public boolean getPermission(CommandSender sender) {
        return sender.hasPermission(BaseInfo.adminMainPermission);
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        sender.sendMessage("§b=== ServerHelper VERSION info ===");
        sender.sendMessage(main.getPluginInfo());
        sender.sendMessage("§b==================================");
        return true;
    }

    @Override
    public CommandParamData[] getCommandParamData() {
        return new CommandParamData[0];
    }
}