package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.*;
import cn.nukkit.level.Level;
import cn.nukkit.potion.Effect;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import cn.stevei5mc.serverhelper.nukkit.utils.BaseUtils;
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
        if (Server.getInstance().getOnlinePlayers().size() >= 2) {
            ArrayList<String> players = new ArrayList<>();
            for (Player p : Server.getInstance().getOnlinePlayers().values()) {
                if (p == player) { //跳过自己
                    continue;
                }
                players.add(p.getName());
            }
            AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("Patrol system");
            custom.addElement(new ElementLabel("选择一名玩家进行巡查或在输入框中填写玩家名称，如果在输入框中输入玩家名称则选择框自动失效"));
            custom.addElement(new ElementDropdown("选择玩家",players));
            custom.addElement(new ElementInput("输入指定玩家名称"));
            custom.addElement(new ElementLabel("如果选择隐身模式，则需要手动脱下身上的装备否则会被其他玩家发现"));
            custom.addElement(new ElementToggle("旁观者模式/隐身模式"));
            custom.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
            custom.onResponded((formResponseCustom, player1) -> {
                try {
                    String target;
                    String target2 = formResponseCustom.getInputResponse(2);
                    if (!target2.equals("")) {
                        target = target2;
                    }else {
                        target = formResponseCustom.getDropdownResponse(1).getElementContent();
                    }
                    teleportToTarget(player1, BaseUtils.getPlayer(target, player1),formResponseCustom.getToggleResponse(4));
                }catch (Exception ignore) {}
            });
            player.showFormWindow(custom);
        }else {
            player.sendMessage("没有足够的在线玩家");
        }
    }

    public static void sendRandomPatrolSystemUi(@NotNull Player player) {
        ArrayList<String> mapName = new ArrayList<>();
        for (Level level : main.getServer().getLevels().values()) {
            mapName.add(level.getName());
        }
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("Patrol system");
        custom.addElement(new ElementLabel("设置随机模式，点击确认后将会被传送至随机到的玩家身边"));
        custom.addElement(new ElementStepSlider("随机模式", Arrays.asList(
                "全部世界", "当前世界", "指定世界"
        )));
        custom.addElement(new ElementDropdown("选择一个世界", mapName));
        custom.addElement(new ElementLabel("如果选择隐身模式，则需要手动脱下身上的装备否则会被其他玩家发现"));
        custom.addElement(new ElementToggle("旁观者模式/隐身模式"));
        custom.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
        custom.onResponded((formResponseCustom, player1) -> {
            try {
                teleportToTarget(player1,
                    BaseUtils.getRandomPlayer(formResponseCustom.getStepSliderResponse(1).getElementID(),player1,formResponseCustom.getDropdownResponse(2).getElementContent()),
                    formResponseCustom.getToggleResponse(4));
            }catch (Exception ignore) {}
        });
        player.showFormWindow(custom);
    }

    private static void teleportToTarget(@NotNull Player admin,Player target,Boolean patrolMode) {
        if (target.isOnline()) {
            if (patrolMode) {
                admin.addEffect(Effect.getEffect(14).setDuration(12000).setAmplifier(5).setVisible(false));
            }else {
                admin.setGamemode(3);
            }
            admin.addEffect(Effect.getEffect(16).setDuration(12000).setAmplifier(5).setVisible(false));
            admin.teleport(target);
            admin.sendMessage("你已传送至："+target.getName());
        }else {
            admin.sendMessage("目标玩家不在线，请选择其他玩家");
        }
    }
}