package cn.stevei5mc.serverhelper.nukkit.form.manage.players;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.stevei5mc.serverhelper.common.utils.PlayerDeviceInfoUtil;
import cn.stevei5mc.serverhelper.nukkit.form.MainForm;
import cn.stevei5mc.serverhelper.nukkit.utils.PlayerUtils;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import org.jetbrains.annotations.NotNull;

public class ManagePlayersForm {
    public static void sendManagePlayersSystemUi(@NotNull Player admin){
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-managerPlayer-title"));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-button-selectPlayer")).onClicked(ManagePlayersForm::sendSelectPlayerUi));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-button-shortcutFeature")).onClicked(ManagePlayersForm::sendManageFeatureList));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-button-back")).onClicked(MainForm::mainMenu));
        admin.showFormWindow(simple);
    }

    // 玩家选择器
    public static void sendSelectPlayerUi(@NotNull Player admin) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom(baseLang.translateString("form-managerPlayer-selectPlayer-title"));
        custom.addElement(new ElementLabel(baseLang.translateString("form-managerPlayer-selectPlayer-description")));
        custom.addElement(new ElementDropdown(baseLang.translateString("form-managerPlayer-selectPlayer-dropdown"), PlayerUtils.getOnlinePlayers(admin,true)));
        custom.addElement(new ElementInput(baseLang.translateString("form-managerPlayer-selectPlayer-input")));
        custom.onClosed(ManagePlayersForm::sendManagePlayersSystemUi);
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
        });
        admin.showFormWindow(custom);
    }

    // 快捷功能菜单
    public static void sendManageFeatureList(@NotNull Player admin) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-managerPlayer-shortcutFeature-title"));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-patrol")).onClicked(PlayerPatrolSystemForm::sendSelectPatrolPlayerUi));
        // TODO: 到时候这里放置查询玩家背包的快捷入口
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-button-back")).onClicked(MainForm::mainMenu));
        admin.showFormWindow(simple);
    }

    // 玩家管理功能菜单
    public static void sendManageTargetPlayerSystem(@NotNull Player admin, Player target) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-managerPlayer-fullFeature-title"), baseLang.translateString("form-managerPlayer-description-targetPlayer", target.getName()));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-ban")));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-mute")));
        if (target.isOnline()) {
            simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-kick")));
            simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-warn")));
            simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-patrol")).onClicked(p -> PlayerPatrolSystemForm.confirmTargetPlayerMenu(admin,target)));
            simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-fullFeature-button-queryInfo")).onClicked(p -> sendPlayerInfoUi(admin,target)));
        }
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-button-back")).onClicked(ManagePlayersForm::sendSelectPlayerUi));
        admin.showFormWindow(simple);
    }

    // 显示目标玩家的信息
    public static void sendPlayerInfoUi(@NotNull Player admin, Player target) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowSimple simple = new AdvancedFormWindowSimple(baseLang.translateString("form-managerPlayer-queryInfo-title"));
        simple.setContent(
                baseLang.translateString("form-managerPlayer-queryInfo-description", target.getName(), target.getLoginChainData().getXUID(), target.getLoginChainData().getClientUUID(),
                PlayerDeviceInfoUtil.getDeviceOS(target.getLoginChainData().getDeviceOS()), target.getLoginChainData().getDeviceModel(), target.getLoginChainData().getDeviceId(),
                target.getAddress()+":"+target.getPort(), target.getLoginChainData().getServerAddress(), target.getPing(),
                target.getLoginChainData().getGameVersion(), target.getLoginChainData().getLanguageCode(), PlayerDeviceInfoUtil.getPlayerUi(target.getLoginChainData().getUIProfile()), PlayerDeviceInfoUtil.getDeviceControls(target.getLoginChainData().getCurrentInputMode()),
                Math.round(target.getX()), Math.round(target.getY()), Math.round(target.getZ()), target.getLevel().getName()
        ));
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-managerPlayer-queryInfo-button-update")).onClicked(player -> sendPlayerInfoUi(admin,target)));
        // TODO: 到时候查询玩家背包的入口放在这里
        simple.addButton(new ResponseElementButton(baseLang.translateString("form-button-back")).onClicked(player -> sendManageTargetPlayerSystem(admin,target)));
        admin.showFormWindow(simple);
    }
}