package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.stevei5mc.serverhelper.nukkit.gui.manage.players.ManagePlayersGui;
import cn.stevei5mc.serverhelper.nukkit.gui.manage.server.ManageServerGui;
import org.jetbrains.annotations.NotNull;

public class MainGui {
    private MainGui() {
        throw new RuntimeException("Error");
    }

    public static void sendMain(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("ServerHelper","ServerHelper");
        simple.addButton(new ResponseElementButton("管理玩家").onClicked(ManagePlayersGui::sendManagePlayersSystemUi));
        simple.addButton(new ResponseElementButton("管理服务器").onClicked(ManageServerGui::sendManageServerUi));
        player.showFormWindow(simple);
    }
}