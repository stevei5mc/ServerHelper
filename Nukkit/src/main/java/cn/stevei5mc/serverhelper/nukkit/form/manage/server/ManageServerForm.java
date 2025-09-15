package cn.stevei5mc.serverhelper.nukkit.form.manage.server;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.stevei5mc.autorestart.gui.Admin;
import cn.stevei5mc.serverhelper.nukkit.form.MainForm;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import org.jetbrains.annotations.NotNull;

public class ManageServerForm {
    public static void sendManageServerUi(@NotNull Player player) {
        Language baseLang = PluginI18n.getBaseLang(player);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-managerServer-title"));
        if (Server.getInstance().getPluginManager().getPlugin("AutoRestart") != null) {
            simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerServer-button-restart")).onClicked(Admin::sendMain));
        }
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-button-back")).onClicked(MainForm::mainMenu));
        player.showFormWindow(simple);
    }
}