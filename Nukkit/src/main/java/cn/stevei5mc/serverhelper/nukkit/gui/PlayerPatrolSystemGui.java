package cn.stevei5mc.serverhelper.nukkit.gui;

import cn.lanink.gamecore.form.element.ResponseElementButton;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowCustom;
import cn.lanink.gamecore.form.windows.AdvancedFormWindowSimple;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.level.Level;
import cn.nukkit.potion.Effect;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
            custom.addElement(new ElementLabel("选择一名玩家进行巡查\n\n"));
            custom.addElement(new ElementDropdown("选择玩家",players));
            custom.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
            custom.onResponded((formResponseCustom, player1) -> {
                try {
                    Player target = Server.getInstance().getPlayer(formResponseCustom.getDropdownResponse(1).getElementContent());
                    teleportToTarget(player1,target);
                }catch (NullPointerException ignore) {
                    player1.sendMessage("目标玩家不在线，请选择其他玩家");
                }
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
        custom.addElement(new ElementLabel("随机巡查一名玩家\n\n"));
        custom.addElement(new ElementStepSlider("随机模式", Arrays.asList(
                "全部世界", "当前世界", "指定世界"
        )));
        custom.addElement(new ElementDropdown("选择一个世界", mapName));
        custom.onClosed(PlayerPatrolSystemGui::sendPatrolSystemMainUi);
        custom.onResponded((formResponseCustom, player1) -> {
            int modeId = formResponseCustom.getStepSliderResponse(1).getElementID();
            List<Player> players = new ArrayList<>(Server.getInstance().getOnlinePlayers().values());
            players.remove(player);  //跳过自己
            if (modeId == 1) {
                players.removeIf(player2 -> !player1.getLevel().getName().equals(player2.getLevel().getName()));
            } else if (modeId == 2) {
                String world = formResponseCustom.getDropdownResponse(2).getElementContent();
                players.removeIf(player2 -> !world.equals(player2.getLevel().getName()));
            }
            if (!players.isEmpty()) {
                Player target = players.get(new Random().nextInt(players.size()));
                teleportToTarget(player1, target);
            } else {
                player1.sendMessage("没有符合条件的玩家");
            }
        });
        player.showFormWindow(custom);
    }

    private static void teleportToTarget(@NotNull Player admin,Player target) {
        if (target.isOnline()) {
            admin.setGamemode(3);
            admin.addEffect(Effect.getEffect(16).setDuration(12000).setAmplifier(5).setVisible(false));
            admin.teleport(target);
            admin.sendMessage("你已传送至："+target.getName());
        }else {
            admin.sendMessage("目标玩家不在线，请选择其他玩家");
        }
    }
}