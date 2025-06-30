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

    // 玩家选择器
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
            if (!target.equals("§c§lPlayer not found")) {
                sendManageTargetPlayerSystem(player1,target);
            }
        });
        custom.onClosed(ManagePlayersGui::sendManagePlayersSystemUi);
        player.showFormWindow(custom);
    }

    // 快捷功能菜单
    public static void sendManageFeatureList(@NotNull Player player) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理功能");
        simple.addButton(new ResponseElementButton("巡查系统").onClicked(PlayerPatrolSystemGui::sendSelectPatrolPlayerUi));
        // TODO: 到时候这里放置查询玩家背包的快捷入口
        simple.addButton(new ResponseElementButton("返回").onClicked(MainGui::sendMain));
        player.showFormWindow(simple);
    }

    // 玩家管理功能菜单
    public static void sendManageTargetPlayerSystem(@NotNull Player player,String target) {
        Player targetPlayer = PlayerUtils.getPlayer(target);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理目标玩家","目标玩家： "+target);
        simple.addButton(new ResponseElementButton("封禁"));
        simple.addButton(new ResponseElementButton("禁言"));
        if (targetPlayer != null && targetPlayer.isOnline()) {
            simple.addButton(new ResponseElementButton("踢出"));
            simple.addButton(new ResponseElementButton("警告"));
            simple.addButton(new ResponseElementButton("巡查").onClicked(player1 -> PlayerPatrolSystemGui.sendConfirmUi(player1,targetPlayer)));
            simple.addButton(new ResponseElementButton("查询该玩家信息").onClicked(player1 -> sendPlayerInfoUi(player1,targetPlayer)));
        }
        simple.addButton(new ResponseElementButton("返回").onClicked(ManagePlayersGui::sendSelectPlayerUi));
        player.showFormWindow(simple);
    }

    // 显示目标玩家的信息
    public static void sendPlayerInfoUi(@NotNull Player player,Player target) {
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple("管理系统");
        simple.setContent(
                "玩家： "+target.getName()+" XUID: "+target.getLoginChainData().getXUID()+"\n"
                +"设备系统： "+PlayerUtils.getDeviceOS(target.getLoginChainData().getDeviceOS())+" 设备型号： "+target.getLoginChainData().getDeviceModel()+"\n"
                +"设备ID： "+target.getLoginChainData().getDeviceId()+"\n"
                +"操作方式： "+PlayerUtils.getDeviceControls(target.getLoginChainData().getCurrentInputMode())+" UI： "+PlayerUtils.getPlayerUi(target.getLoginChainData().getUIProfile())+"\n"
                +"链接IP: "+target.getLoginChainData().getServerAddress()+"\n"
                +"登录Ip： "+target.getAddress()+":"+target.getPort()+" 延迟："+target.getPing()+"\n"
                +"客户端版本： "+target.getLoginChainData().getGameVersion()+"\n"
                +"所在位置： (X="+Math.round(target.getX())+" Y="+Math.round(target.getY())+" Z="+Math.round(target.getZ())+" Level="+target.getLevel().getName()+")\n"
        );
        simple.addButton(new ResponseElementButton("刷新").onClicked(player1 -> sendPlayerInfoUi(player,target)));
        // TODO: 到时候查询玩家背包的入口放在这里
        simple.addButton(new ResponseElementButton("返回").onClicked(player1 -> sendManageTargetPlayerSystem(player,target.getName())));
        player.showFormWindow(simple);
    }
}