package cn.stevei5mc.serverhelper.waterdogpe.commands.maimcmd.sub;

import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.commands.base.SubCommandBase;
import dev.waterdog.waterdogpe.command.CommandSender;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public class ReloadCmd extends SubCommandBase {
    public ReloadCmd(String name) {
        super(name);
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean getPermission(CommandSender sender) {
        return sender.hasPermission(BaseInfo.reloadPermission);
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        main.loadConfig();
        sender.sendMessage(main.getMessagePrefix() + "§a配置文件重载成功");
        return true;
    }

    @Override
    public CommandParamData[] getCommandParamData() {
        return new CommandParamData[0];
    }
}