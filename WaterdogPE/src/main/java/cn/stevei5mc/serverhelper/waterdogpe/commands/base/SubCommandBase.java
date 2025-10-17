package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.CommandSender;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public abstract class SubCommandBase {

    protected ServerHelperMain main = ServerHelperMain.getInstance();
    private String name;

    /**
     * @param name SubCommand name
     */
    public SubCommandBase(String name) {
        this.name = name.toLowerCase();
    }

    /**
     * @return Get subCommand name
     */
    public String getName() {
        return name.toLowerCase();
    }

    /**
     * Get subCommand aliases
     * @return String[]
     */
    public abstract String[] getAliases();

    /**
     * SubCommand permission
     * @param sender CommandSender
     * @return Permission state
     */
    public abstract boolean getPermission(CommandSender sender);

    /**
     * @return SubCommand execute state ( true or false )
     */
    public abstract boolean execute(CommandSender sender, String s, String[] args);

    /**
     * Get commandParam
     * @return CommandParam
     */
    public abstract CommandParamData[] getCommandParamData();
}