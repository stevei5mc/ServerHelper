package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import org.cloudburstmc.protocol.bedrock.data.command.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在下方列表之中的方法是有修改的需求时再进行重写修改，一般情况下不需要进行重写也可以正常使用的。<br>
 * 而不在下方列表中的方法是必须进行重写的。
 * <br>
 * The methods listed below only need to be overridden when modification is required; under normal circumstances, they can function properly without being rewritten. <br>
 * In contrast, methods not listed below must be overridden.
 * <br>列表 ( List ) : <br>- onExecute <br>- buildCommandOverloads <br>- addSubCommand <br>- checkPermission <br>- getAliasesArray
 */
public abstract class CommandBase extends Command {

    protected ServerHelperMain main = ServerHelperMain.getInstance();
    private final ArrayList<SubCommandBase> subCmdList = new ArrayList<>();
    private final ConcurrentHashMap<String, Integer> subCmdMap = new ConcurrentHashMap<>();

    /**
     * @param name Command name
     * @param description Command description
     * @param permission Command permission
     */
    public CommandBase(String name, String description, String permission) {
        super(name.toLowerCase(), CommandSettings.builder().setDescription(description).setPermission(permission).build());
    }

    /**
     * @param name Command name
     * @param description Command description
     * @param permission Command permission
     * @param aliases Command aliases
     */
    public CommandBase(String name, String description, String permission, String[] aliases) {
        super(name.toLowerCase(), CommandSettings.builder().setDescription(description).setPermission(permission).setAliases(aliases).build());
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (checkPermission(sender)) {
            if (args.length > 0) {
                String subCmdName = args[0].toLowerCase();
                if(subCmdMap.containsKey(subCmdName)) {
                    SubCommandBase subCmd = subCmdList.get(subCmdMap.get(subCmdName));
                    if (subCmd.getPermission(sender)) {
                        return subCmd.execute(sender, s, args);
                    }else if (sender.isPlayer()) {
                        sender.sendMessage(main.getMessagePrefix() + "§c你没有权限使用此命令！");
                    }else {
                        sender.sendMessage(main.getMessagePrefix() + "§c请在游戏内使用此命令！");
                    }
                    return true;
                }
            }
            sendHelp(sender);
            return true;
        }
        sender.sendMessage(main.getMessagePrefix() + "§c你没有权限使用此命令！");
        return true;
    }

    @Override
    protected CommandOverloadData[] buildCommandOverloads() {
        List<CommandOverloadData> overloadData = new ArrayList<>();
        for (SubCommandBase subCmd : subCmdList) {
            Map<String, Set<CommandEnumConstraint>> map = new LinkedHashMap<>();
            map.put(subCmd.getName().toLowerCase(), EnumSet.of(CommandEnumConstraint.ALLOW_ALIASES));
            CommandParamData baseParam = new CommandParamData();
            baseParam.setName(subCmd.getName().toLowerCase());
            baseParam.setOptional(false);
            baseParam.setType(CommandParam.TEXT);
            baseParam.setEnumData(new CommandEnumData(subCmd.getName().toLowerCase(), map, false));

            LinkedList<CommandParamData> paramData = new LinkedList<>();
            paramData.add(baseParam);
            paramData.addAll(Arrays.asList(subCmd.getCommandParamData()));

            overloadData.add(new CommandOverloadData(false, paramData.toArray(new CommandParamData[0])));
        }
        return overloadData.toArray(new CommandOverloadData[0]);
    }

    /**
     * Get command aliases
     * @return Command aliases
     */
    public String[] getAliasesArray() {
        return getAliases().toArray(new String[0]);
    }

    /**
     * Check command sender permission
     * @param sender CommandSender
     * @return Sender permission state
     */
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission(getPermission());
    }

    /**
     * Send command help
     * @param sender CommandSender
     */
    public abstract void sendHelp(CommandSender sender);

    /**
     * @param subCmd SubCommand
     */
    protected void addSubCommand(SubCommandBase subCmd) {
        subCmdList.add(subCmd);
        int subCmdId = subCmdList.size() - 1;
        subCmdMap.put(subCmd.getName().toLowerCase(), subCmdId);
        for (String alias : subCmd.getAliases()) {
            subCmdMap.put(alias, subCmdId);
        }
    }
}