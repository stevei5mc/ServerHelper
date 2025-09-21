package cn.stevei5mc.serverhelper.waterdogpe.commands;

import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import dev.waterdog.waterdogpe.command.CommandSender;

public class ServerHelperMainCmd extends CommandBase {
    public ServerHelperMainCmd(String name, String description, String permission, String[] aliases) {
        super(name, description, permission, aliases);
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        String msgPrefix = main.getMessagePrefix();
        if (checkPermission(sender, getPermission())) {
            if (args.length > 0) {
                switch (args[0]) {
                    case "version":
                        sender.sendMessage("§b=== ServerHelper VERSION info ===");
                        sender.sendMessage(BaseInfo.VERSION);
                        sender.sendMessage(BaseInfo.COMMIT_ID);
                        sender.sendMessage(BaseInfo.BRANCH);
                        sender.sendMessage("§b==================================");
                        break;
                    case "reload":
                        if (checkPermission(sender, BaseInfo.reloadPermission)) {
                            main.loadConfig();
                            sender.sendMessage(msgPrefix+"§a配置文件重载成功");
                        }
                        break;
                    default:
                        sendHelp(sender);
                }
            }else {
                sendHelp(sender);
            }
        }
        return true;
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wdshr [version | reload]");
    }
}