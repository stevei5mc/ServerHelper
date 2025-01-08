package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.level.Level;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerPatrolSystemGui {
    private PlayerPatrolSystemGui() {
        throw new RuntimeException("Error");
    }
    
    private static ServerHelperMain main = ServerHelperMain.getInstance();

    public static void sendPatrolSystemMainUi(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("Patrol system","巡查玩家的游玩情况");
        simple.addButton(new ResponseElementButton("指定巡查").onClicked(PlayerPatrolSystemGui::sendDesignatedPatrolSystem));
        simple.addButton(new ResponseElementButton("随机巡查").onClicked(PlayerPatrolSystemGui::sendRandomPatrolSystemUi));
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        player.showFormWindow(simple);
    }

    public static void sendDesignatedPatrolSystem(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("Patrol system","巡查指定玩家的游玩情况");
        simple.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
        player.showFormWindow(simple);
    }

    public static void sendRandomPatrolSystemUi(@NotNull Player player) {
        ArrayList<String> mapName = new ArrayList<>();
        for (Level level : main.getServer().getLevels().values()) {
            mapName.add(level.getName());
        }
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("Patrol system");
        custom.addElement(new ElementLabel("随机巡查一名玩家\n\n"));
        custom.addElement(new ElementStepSlider("随机模式", Arrays.asList(
                "全部世界","当前世界","指定世界"
        )));
        custom.addElement(new ElementDropdown("选择一个指定的世界",mapName));
        custom.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
        player.showFormWindow(custom);
    }
}
