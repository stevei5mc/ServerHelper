package cn.stevei5mc.serverhelper.nukkit.commands.admin;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.base.BaseCommand;
import cn.stevei5mc.serverhelper.nukkit.form.MainForm;

public class AdminCmd extends BaseCommand {
    public AdminCmd(String name, String description) {
        super(name, description);
        this.setPermission(PermissionsInfo.ADMIN_MAIN.getPermission());
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

    @Override
    public void sendUI(Player player) {
        MainForm.mainMenu(player);
    }
}
