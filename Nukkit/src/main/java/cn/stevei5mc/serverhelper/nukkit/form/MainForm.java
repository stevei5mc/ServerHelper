package cn.stevei5mc.serverhelper.nukkit.form;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.stevei5mc.serverhelper.nukkit.form.manage.players.ManagePlayersForm;
import cn.stevei5mc.serverhelper.nukkit.form.manage.server.ManageServerForm;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import org.jetbrains.annotations.NotNull;

public class MainForm {
    public static void mainMenu(@NotNull Player player) {
        Language baseLang = PluginI18n.getBaseLang(player);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-main-title"));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-main-button-managerPlayer")).onClicked(ManagePlayersForm::sendManagePlayersSystemUi));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-main-button-managerServer")).onClicked(ManageServerForm::sendManageServerUi));
        player.showFormWindow(simple);
    }
}