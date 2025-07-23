package cn.stevei5mc.serverhelper.nukkit.gui.manage.players;

import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.*;
import cn.nukkit.level.Level;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import cn.stevei5mc.serverhelper.nukkit.utils.PlayerUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerPatrolSystemGui {
    private static final ServerHelperMain main = ServerHelperMain.getInstance();

    private PlayerPatrolSystemGui() {
        throw new RuntimeException("Error");
    }

    // 选择需要巡查的玩家
    public static void sendSelectPatrolPlayerUi(@NotNull Player player) {
        // 获取全部加载的世界名
        ArrayList<String> mapName = new ArrayList<>();
        for (Level level : main.getServer().getLevels().values()) {
            mapName.add(level.getName());
        }
        // ui
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("巡查系统");
        custom.addElement(new ElementStepSlider("搜索模式",Arrays.asList("指定搜索","随机搜索")));
        custom.addElement(new ElementLabel("选择一名玩家进行巡查或在输入框中填写玩家名，如果在输入框中输入玩家名称则选择框自动失效，但如果选择随机模式则两者都失效"));
        custom.addElement(new ElementDropdown("选择玩家",PlayerUtils.getOnlinePlayers(player,true)));
        custom.addElement(new ElementInput("输入指定玩家的名称"));
        custom.addElement(new ElementStepSlider("随机模式", Arrays.asList("全部世界", "当前世界", "指定世界")));
        custom.addElement(new ElementDropdown("选择世界",mapName));
        custom.addElement(new ElementLabel("如果选择隐身模式，则需要手动脱下身上的装备否则会被其他玩家发现"));
        custom.addElement(new ElementToggle("旁观者模式 / 隐身模式"));
        custom.onResponded((formResponseCustom, player1) -> {
            if (Server.getInstance().getOnlinePlayers().size() >= 2) {
                Player targetPlayer = null;
                switch (formResponseCustom.getStepSliderResponse(0).getElementID()) {
                    case 0:
                        String target;
                        if (!formResponseCustom.getInputResponse(3).equals("")) {
                            target = formResponseCustom.getInputResponse(3);
                        }else {
                            target = formResponseCustom.getDropdownResponse(2).getElementContent();
                        }
                        if (target.equals("§c§lPlayer not found")) {
                            targetPlayer = PlayerUtils.getPlayer(target);
                        }
                        break;
                    default:
                        targetPlayer = PlayerUtils.getRandomPlayer(formResponseCustom.getStepSliderResponse(4).getElementID(),player1,formResponseCustom.getDropdownResponse(5).getElementContent());
                        break;
                }
                if (targetPlayer != null) {
                    PlayerUtils.teleportToPatrolTarget(player1,targetPlayer,formResponseCustom.getToggleResponse(7));
                }else {
                    player1.sendMessage("无法找到目标玩家，请确保目标玩家在线并重新尝试");
                }
            }else{
                player1.sendMessage("没有足够的在线玩家，至少需要两名玩家在线");
            }
        });
        player.showFormWindow(custom);
    }

    // 选择玩家确认菜单
    public static void sendConfirmUi(@NotNull Player player, Player target) {
        AdvancedFormWindowCustom custom = new AdvancedFormWindowCustom("巡查系统");
        custom.addElement(new ElementLabel("目标玩家: "+target.getName()));
        custom.addElement(new ElementToggle("旁观者模式 / 隐身模式"));
        custom.onResponded((formResponseCustom, player1) -> {
            PlayerUtils.teleportToPatrolTarget(player,target,formResponseCustom.getToggleResponse(1));
        });
        player.showFormWindow(custom);
    }
}