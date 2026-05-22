package cn.stevei5mc.serverhelper.nukkit.commands.maincmd.sub;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseSubCommand;

public class ReloadCmd extends BaseSubCommand {
    public ReloadCmd(String name) {
        super(name);
    }

    @Override
    public boolean canUser(CommandSender sender) {
        return sender.hasPermission(PermissionsInfo.ADMIN_RELOAD.getPermission());
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        main.loadConfig();
        sender.sendMessage(main.getMessagePrefix()+"§a配置文件重载成功");
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[0];
    }
}