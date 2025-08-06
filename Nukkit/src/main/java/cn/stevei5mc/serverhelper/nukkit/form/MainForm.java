package cn.stevei5mc.serverhelper.nukkit.form;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.stevei5mc.serverhelper.nukkit.form.manage.players.ManagePlayersForm;
import cn.stevei5mc.serverhelper.nukkit.form.manage.server.ManageServerForm;
import org.jetbrains.annotations.NotNull;

public class MainForm {
    public static void mainMenu(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("ServerHelper","ServerHelper");
        simple.addButton(new ResponseElementButton("管理玩家").onClicked(ManagePlayersForm::sendManagePlayersSystemUi));
        simple.addButton(new ResponseElementButton("管理服务器").onClicked(ManageServerForm::sendManageServerUi));
        player.showFormWindow(simple);
    }
}