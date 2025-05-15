package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.stevei5mc.serverhelper.nukkit.gui.manage.players.PlayerPatrolSystemGui;
import cn.stevei5mc.serverhelper.nukkit.utils.PlayerUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ManagePlayersGui {
    private ManagePlayersGui() {
        throw new RuntimeException("Error");
    }

    public static void sendManagePlayersSystemUi(@NotNull Player player){
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理玩家");
        simple.addButton(new ResponseElementButton("选择玩家").onClicked(ManagePlayersGui::sendSelectPlayerUi));
        simple.addButton(new ResponseElementButton("功能快捷通道").onClicked(ManagePlayersGui::sendManageFeatureList));
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        player.showFormWindow(simple);
    }

    public static void sendSelectPlayerUi(@NotNull Player player) {
        ArrayList<String> players = new ArrayList<>();
        for (Player p : Server.getInstance().getOnlinePlayers().values()) {
            if (p == player) { //跳过自己
                continue;
            }
            players.add(p.getName());
        }
        if (players.isEmpty()) {
            players.add("§c§lPlayer not found");
        }
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("选择玩家");
        custom.addElement(new ElementLabel("选择一名玩家或在输入框中填写玩家名，如果在输入框中输入玩家名称则选择框自动失效"));
        custom.addElement(new ElementDropdown("选择玩家",players));
        custom.addElement(new ElementInput("输入指定玩家名称"));
        custom.onResponded((formResponseCustom, player1) -> {
            String target;
            if (!formResponseCustom.getInputResponse(2).equals("")) {
                target = formResponseCustom.getInputResponse(2);
            }else {
                target = formResponseCustom.getDropdownResponse(1).getElementContent();
            }
            sendManageTargetPlayerSystem(player1,target);
        });
        custom.onClosed(ManagePlayersGui::sendManagePlayersSystemUi);
        player.showFormWindow(custom);
    }

    public static void sendManageFeatureList(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理功能");
        simple.addButton(new ResponseElementButton("巡查系统").onClicked(PlayerPatrolSystemGui::sendSelectPatrolPlayerUi));
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        player.showFormWindow(simple);
    }

    public static void sendManageTargetPlayerSystem(@NotNull Player player,String target) {
        Player targetPlayer = PlayerUtils.getPlayer(target);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理目标玩家","目标玩家： "+target);
        simple.addButton(new ResponseElementButton("封禁"));
        simple.addButton(new ResponseElementButton("禁言"));
        if (targetPlayer != null && targetPlayer.isOnline()) {
            simple.addButton(new ResponseElementButton("踢出"));
            simple.addButton(new ResponseElementButton("警告"));
            simple.addButton(new ResponseElementButton("巡查").onClicked(player1 -> {
                PlayerPatrolSystemGui.sendConfirmWindow(player,targetPlayer);
            }));
        }
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        player.showFormWindow(simple);
    }
}