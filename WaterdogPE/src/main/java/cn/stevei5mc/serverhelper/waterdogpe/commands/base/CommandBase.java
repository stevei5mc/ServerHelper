package cn.stevei5mc.serverhelper.waterdogpe.commands.base;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18N;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;

public abstract class CommandBase extends Command {
    protected ServerHelperMain main = ServerHelperMain.getInstance();

    public CommandBase(String name, String description, String permission) {
        super(name,CommandSettings.builder().setDescription(description).setPermission(permission).build());
    }

    public CommandBase(String name, String description, String permission, String[] aliases) {
        super(name,CommandSettings.builder().setDescription(description).setPermission(permission).setAliases(aliases).build());
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        return false;
    }

    public boolean checkPermission(CommandSender sender,String permission) {
        if(sender.hasPermission(permission)) {
            return true;
        }else {
            sender.sendMessage(main.getMessagePrefix() + PluginI18N.getBaseLang(sender).translateString("command-message-notPermission"));
            return false;
        }
    }
}