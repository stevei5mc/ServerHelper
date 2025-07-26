package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
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

    public static void sendManagePlayersSystemUi(@NotNull Player admin){
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理玩家");
        simple.addButton(new ResponseElementButton("选择玩家").onClicked(ManagePlayersGui::sendSelectPlayerUi));
        simple.addButton(new ResponseElementButton("功能快捷通道").onClicked(ManagePlayersGui::sendManageFeatureList));
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        admin.showFormWindow(simple);
    }

    // 玩家选择器
    public static void sendSelectPlayerUi(@NotNull Player admin) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("选择玩家");
        custom.addElement(new ElementLabel("选择一名玩家或在输入框中填写玩家名，如果在输入框中输入玩家名称则选择框自动失效"));
        custom.addElement(new ElementDropdown("选择玩家",PlayerUtils.getOnlinePlayers(admin,true)));
        custom.addElement(new ElementInput("输入指定玩家名称"));
        custom.onResponded((formResponseCustom, player1) -> {
            String target;
            if (!formResponseCustom.getInputResponse(2).equals("")) {
                target = formResponseCustom.getInputResponse(2);
            }else {
                target = formResponseCustom.getDropdownResponse(1).getElementContent();
            }
            if (!target.equals("§c§lPlayer not found")) {
                Player targetPlayer = PlayerUtils.getPlayer(target);
                sendManageTargetPlayerSystem(player1,targetPlayer);
            }
            custom.onClosed(ManagePlayersGui::sendManagePlayersSystemUi);
        });
        admin.showFormWindow(custom);
    }

    // 快捷功能菜单
    public static void sendManageFeatureList(@NotNull Player admin) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理功能");
        simple.addButton(new ResponseElementButton("巡查系统").onClicked(PlayerPatrolSystemGui::sendSelectPatrolPlayerUi));
        // TODO: 到时候这里放置查询玩家背包的快捷入口
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        admin.showFormWindow(simple);
    }

    // 玩家管理功能菜单
    public static void sendManageTargetPlayerSystem(@NotNull Player admin, Player target) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理目标玩家","目标玩家： "+target);
        simple.addButton(new ResponseElementButton("封禁"));
        simple.addButton(new ResponseElementButton("禁言"));
        if (target != null && target.isOnline()) {
            simple.addButton(new ResponseElementButton("踢出"));
            simple.addButton(new ResponseElementButton("警告"));
            simple.addButton(new ResponseElementButton("巡查").onClicked(player1 -> PlayerPatrolSystemGui.sendConfirmUi(player1,target)));
            simple.addButton(new ResponseElementButton("查询该玩家信息").onClicked(player1 -> sendPlayerInfoUi(player1,target)));
        }
        simple.addButton(new ResponseElementButton("返回").onClicked(ManagePlayersGui::sendSelectPlayerUi));
        admin.showFormWindow(simple);
    }

    // 显示目标玩家的信息
    public static void sendPlayerInfoUi(@NotNull Player admin, Player target) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理系统");
        simple.setContent(
                "玩家="+target.getName()+" XUID="+target.getLoginChainData().getXUID()+"\n"
                +"UUID="+target.getLoginChainData().getClientUUID()+"\n"
                +"设备系统="+PlayerUtils.getDeviceOS(target.getLoginChainData().getDeviceOS())+" 设备型号="+target.getLoginChainData().getDeviceModel()+"\n"
                +"设备ID="+target.getLoginChainData().getDeviceId()+"\n"
                +"操作方式="+PlayerUtils.getDeviceControls(target.getLoginChainData().getCurrentInputMode())+ " UI="+PlayerUtils.getPlayerUi(target.getLoginChainData().getUIProfile())+ " 客户端版本="+target.getLoginChainData().getGameVersion()+"\n"
                +"链接IP="+target.getLoginChainData().getServerAddress()+"\n"
                +"登录Ip="+target.getAddress()+":"+target.getPort()+" 延迟："+target.getPing()+"\n"
                +"所在位置=(X="+Math.round(target.getX())+" Y="+Math.round(target.getY())+" Z="+Math.round(target.getZ())+" Level="+target.getLevel().getName()+")\n"
        );
        simple.addButton(new ResponseElementButton("刷新").onClicked(player -> sendPlayerInfoUi(admin,target)));
        // TODO: 到时候查询玩家背包的入口放在这里
        simple.addButton(new ResponseElementButton("返回").onClicked(player -> sendManageTargetPlayerSystem(admin,target)));
        admin.showFormWindow(simple);
    }
}