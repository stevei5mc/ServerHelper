package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.CommandSender;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOverloadData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public abstract class SubCommandBase {

    protected ServerHelperMain main = ServerHelperMain.getInstance();
    private String name;

    public SubCommandBase(String name) {
        this.name = name.toLowerCase();
    }

    public abstract String[] getAliases();

    public String getName() {
        return name.toLowerCase();
    }

    public abstract boolean getPermission(CommandSender sender);

    public abstract boolean execute(CommandSender sender, String s, String[] args);

    public abstract CommandParamData[] getCommandParamData();
}