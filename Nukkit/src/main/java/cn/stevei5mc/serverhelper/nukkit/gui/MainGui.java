package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import org.jetbrains.annotations.NotNull;

public class MainGui {
    private MainGui() {
        throw new RuntimeException("Error");
    }

    public static void sendMain(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("ServerHelper","ServerHelper");
        simple.addButton(new ResponseElementButton("巡查系统").onClicked(PlayerPatrolSystemGui::sendPatrolSystemMainUi));
        player.showFormWindow(simple);
    }
}
