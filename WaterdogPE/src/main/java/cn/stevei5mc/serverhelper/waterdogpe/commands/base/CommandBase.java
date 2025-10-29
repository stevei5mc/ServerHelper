package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import cn.stevei5mc.serverhelper.waterdogpe.utils.LanguageApi;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18n;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import org.cloudburstmc.protocol.bedrock.data.command.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 下列方法有修改的需要再进行重写，只需重写不在列表之内的方法即可正常使用。<br>
 * The following methods need to be overridden only if modifications are required. Simply override methods not in this list for normal usage.
 *
 * <br><br> onExecute <br> buildCommandOverloads <br> addSubCommand <br> checkPermission <br>
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
        LanguageApi baseLang = PluginI18n.getBaseLang(sender);
        if (checkPermission(sender)) {
            if (args.length > 0) {
                String subCmdName = args[0].toLowerCase();
                if(subCmdMap.containsKey(subCmdName)) {
                    SubCommandBase subCmd = subCmdList.get(subCmdMap.get(subCmdName));
                    if (subCmd.getPermission(sender)) {
                        return subCmd.execute(sender, s, args);
                    }else if (sender.isPlayer()) {
                        sender.sendMessage(main.getMessagePrefix() + baseLang.translateString("command-message-notPermission"));
                    }else {
                        sender.sendMessage(main.getMessagePrefix() + baseLang.translateString("command-message-inGameRun"));
                    }
                    return true;
                }
            }
            sendHelp(sender);
            return true;
        }
        sender.sendMessage(main.getMessagePrefix() + baseLang.translateString("command-message-notPermission"));
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