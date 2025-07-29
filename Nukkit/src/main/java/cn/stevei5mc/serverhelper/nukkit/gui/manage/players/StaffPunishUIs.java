package cn.stevei5mc.serverhelper.nukkit.gui.manage.players;

import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import org.jetbrains.annotations.NotNull;

public class StaffPunishUIs {
    public static void sendBanPlayerUi(@NotNull Player admin, Player target) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("管理系统");
        custom.addElement(new ElementLabel("目标玩家： "+target.getName()+"\n"));
        admin.showFormWindow(custom);
    }

    public static void sendMutePlayerUi(@NotNull Player admin, Player target) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("管理系统");
        custom.addElement(new ElementLabel("目标玩家： "+target.getName()+"\n"));
        admin.showFormWindow(custom);

    }

    public static void sendKickPlayerUi(@NotNull Player admin, Player target) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("管理系统");
        custom.addElement(new ElementLabel("目标玩家： "+target.getName()+"\n"));
        admin.showFormWindow(custom);
    }

    public static void sendWarnPlayerUi(@NotNull Player admin, Player target) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("管理系统");
        custom.addElement(new ElementLabel("目标玩家： "+target.getName()+"\n"));
        admin.showFormWindow(custom);

    }
}
