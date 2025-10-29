package cn.stevei5mc.serverhelper.nukkit.form.manage.players;

import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.*;
import cn.nukkit.level.Level;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import cn.stevei5mc.serverhelper.nukkit.utils.PlayerUtils;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerPatrolSystemForm {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    // 选择需要巡查的玩家 （快捷菜单使用）
    public static void sendSelectPatrolPlayerUi(@NotNull Player admin) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        // 获取全部加载的世界名
        ArrayList<String> mapName = new ArrayList<>();
        for (Level level : main.getServer().getLevels().values()) {
            mapName.add(level.getName());
        }
        // ui
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom(baseLang.translateString("form-managerPlayer-patrol-title"));
        custom.addElement(new ElementToggle(baseLang.translateString("form-managerPlayer-patrol-toggle-searchMode"),true));
        custom.addElement(new ElementLabel(baseLang.translateString("form-managerPlayer-patrol-description-mode")));
        custom.addElement(new ElementDropdown(baseLang.translateString("form-managerPlayer-selectPlayer-dropdown"), PlayerUtils.getOnlinePlayers(admin,true)));
        custom.addElement(new ElementInput(baseLang.translateString("form-managerPlayer-selectPlayer-input")));
        custom.addElement(new ElementStepSlider(baseLang.translateString("form-managerPlayer-patrol-stepSlider-randomMode"), Arrays.asList(baseLang.translateString("form-managerPlayer-patrol-stepSlider-randomMode-allWorld"),
                baseLang.translateString("form-managerPlayer-patrol-stepSlider-randomMode-currentWorld"), baseLang.translateString("form-managerPlayer-patrol-stepSlider-randomMode-selectWorld"))));
        custom.addElement(new ElementDropdown(baseLang.translateString("form-managerPlayer-patrol-dropdown-selectWorld"), mapName));
        custom.addElement(new ElementLabel(baseLang.translateString("form-managerPlayer-patrol-description-warn")));
        custom.addElement(new ElementToggle(baseLang.translateString("form-managerPlayer-patrol-toggle-patrolMode")));
        custom.onClosed(ManagePlayersForm::sendManagePlayersSystemUi);
        custom.onResponded((form, player1) -> {
            if (Server.getInstance().getOnlinePlayers().size() >= 2) {
                Player targetPlayer = null;
                String target = "";
                if (form.getToggleResponse(0)) {
                    if (!form.getInputResponse(3).equals("")) {
                        target = form.getInputResponse(3);
                    }else {
                        target = form.getDropdownResponse(2).getElementContent();
                    }
                    if (target.equals("§c§lPlayer not found")) {
                        targetPlayer = PlayerUtils.getPlayer(target);
                    }
                }else {
                    targetPlayer = PlayerUtils.getRandomPlayer(form.getStepSliderResponse(4).getElementID(),player1,form.getDropdownResponse(5).getElementContent());
                    target = targetPlayer != null ? targetPlayer.getName() : null;
                }
                if (targetPlayer != null && targetPlayer.isOnline()) {
                    PlayerUtils.teleportToPatrolTarget(player1, targetPlayer, form.getToggleResponse(7));
                }else {
                    player1.sendMessage(baseLang.translateString("message-targetPlayer-isOffline", target));
                }
            }else{
                player1.sendMessage(baseLang.translateString("message-minPlayer"));
            }
        });
        admin.showFormWindow(custom);
    }

    // 确认目标玩家菜单
    public static void confirmTargetPlayerMenu(@NotNull Player admin, Player target) {
        Language baseLang = PluginI18n.getBaseLang(admin);
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom(baseLang.translateString("form-managerPlayer-patrol-title"));
        custom.addElement(new ElementLabel(baseLang.translateString("form-managerPlayer-description-targetPlayer", target.getName())));
        custom.addElement(new ElementToggle(baseLang.translateString("form-managerPlayer-patrol-toggle-patrolMode")));
        custom.onClosed(player -> ManagePlayersForm.manageFeatureList(admin, target));
        custom.onResponded((form, player) -> PlayerUtils.teleportToPatrolTarget(admin, target, form.getToggleResponse(1)));
        admin.showFormWindow(custom);
    }
}