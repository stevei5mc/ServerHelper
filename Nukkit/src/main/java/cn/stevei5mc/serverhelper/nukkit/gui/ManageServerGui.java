package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.stevei5mc.autorestart.gui.Admin;
import org.jetbrains.annotations.NotNull;

public class ManageServerGui {
    private ManageServerGui() {
        throw new RuntimeException("Error");
    }

    public static void sendManageServerUi(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理服务器");
        if (Server.getInstance().getPluginManager().getPlugin("AutoRestart") != null) {
            simple.addButton(new ResponseElementButton("重启管理").onClicked(Admin::sendMain));
            simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        }
        player.showFormWindow(simple);
    }
}