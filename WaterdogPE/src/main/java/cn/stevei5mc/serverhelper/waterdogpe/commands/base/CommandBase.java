package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import org.cloudburstmc.protocol.bedrock.data.command.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CommandBase extends Command {
    protected ServerHelperMain main = ServerHelperMain.getInstance();
    private final ArrayList<SubCommandBase> subCmdList = new ArrayList<>();
    private final ConcurrentHashMap<String, Integer> subCmdMap = new ConcurrentHashMap<>();

    public CommandBase(String name, String description, String permission) {
        super(name.toLowerCase(), CommandSettings.builder().setDescription(description).setPermission(permission).build());
    }

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
            Map<String, Set<CommandEnumConstraint>> map1 = new LinkedHashMap<>();
            map1.put(subCmd.getName().toLowerCase(), EnumSet.of(CommandEnumConstraint.ALLOW_ALIASES));
            CommandParamData baseParam = new CommandParamData();
            baseParam.setName(subCmd.getName().toLowerCase());
            baseParam.setOptional(false);
            baseParam.setType(CommandParam.TEXT);
            baseParam.setEnumData(new CommandEnumData(subCmd.getName().toLowerCase(), map1, false));

            LinkedList<CommandParamData> paramData = new LinkedList<>();
            paramData.add(baseParam);
            paramData.addAll(Arrays.asList(subCmd.getCommandParamData()));
            overloadData.add(new CommandOverloadData(false, paramData.toArray(new CommandParamData[0])));
        }
        return overloadData.toArray(new CommandOverloadData[0]);
    }

    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission(getPermission());
    }

    public abstract void sendHelp(CommandSender sender);

    protected void addSubCommand(SubCommandBase subCmd) {
        subCmdList.add(subCmd);
        int subCmdId = subCmdList.size() - 1;
        subCmdMap.put(subCmd.getName().toLowerCase(), subCmdId);
        for (String alias : subCmd.getAliases()) {
            subCmdMap.put(alias, subCmdId);
        }
    }
}