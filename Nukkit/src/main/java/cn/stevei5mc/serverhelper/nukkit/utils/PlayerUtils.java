package cn.stevei5mc.serverhelper.nukkit.utils;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.potion.Effect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerUtils {

    /**
     * 获取指定玩家的信息
     * @param name 玩家名
     * @return 返回目标玩家
     */
    public static Player getPlayer(String name) {
        return Server.getInstance().getPlayer(name);
    }

    /**
     * 获取随机玩家
     * @param randomMode 随机模式（0 = 全部世界，1 = 当前世界，2 = 指定世界）
     * @param admin 管理员(用于排除自己)
     * @return 返回目标玩家
     */
    public static Player getRandomPlayer(int randomMode,Player admin) {
        return getRandomPlayer(randomMode,admin,null);
    }

    /**
     * 获取随机玩家
     * @param randomMode 随机模式（0 = 全部世界，1 = 当前世界，2 = 指定世界）
     * @param admin 管理员(用于排除自己)
     * @param levelName 世界名(randomMode = 0无效)
     * @return 返回目标玩家
     */
    public static Player getRandomPlayer(int randomMode,Player admin,String levelName) {
        List<Player> players = new ArrayList<>(Server.getInstance().getOnlinePlayers().values());
        players.remove(admin);  //跳过自己
        if (randomMode == 1) {
            players.removeIf(player2 -> !admin.getLevel().getName().equals(player2.getLevel().getName()));
        } else if (randomMode == 2) {
            players.removeIf(player2 -> !levelName.equals(player2.getLevel().getName()));
        }
        if (!players.isEmpty()) {
            return players.get(new Random().nextInt(players.size()));
        }else {
            return null;
        }
    }

    /**
     * 传送至巡查目标
     * @param admin 传送者
     * @param target 传送目标
     * @param patrolMode 巡查模式（true = 隐身模式，false = 旁观者模式）
     */
    public static void teleportToPatrolTarget(@NotNull Player admin, Player target, Boolean patrolMode) {
        if (target.isOnline()) {
            if (patrolMode) {
                admin.addEffect(Effect.getEffect(14).setDuration(12000).setAmplifier(5).setVisible(false));
            } else {
                admin.setGamemode(3);
            }
            admin.addEffect(Effect.getEffect(16).setDuration(12000).setAmplifier(5).setVisible(false));
            admin.teleport(target);
            admin.sendMessage("你已传送至：" + target.getName());
        } else {
            admin.sendMessage("目标玩家不在线，请选择其他玩家");
        }
    }
}
