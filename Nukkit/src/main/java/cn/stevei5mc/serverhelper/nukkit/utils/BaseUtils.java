package cn.stevei5mc.serverhelper.nukkit.utils;

import cn.nukkit.Player;
import cn.nukkit.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseUtils {
    public static Player getPlayer(String name,Player admin) {
        Player target = Server.getInstance().getPlayer(name);
        if (target == null) {
            admin.sendMessage("目标玩家不在线，请选择其他玩家");
        }
        return target;
    }

    public static Player getRandomPlayer(Player admin) {
        return getRandomPlayer(0,admin,null);
    }

    public static Player getRandomPlayer(int randomMode,Player admin,String levelName) {
        List<Player> players = new ArrayList<>(Server.getInstance().getOnlinePlayers().values());
        players.remove(admin);  //跳过自己
        if (randomMode == 1) {
            players.removeIf(player2 -> !admin.getLevel().getName().equals(player2.getLevel().getName()));
        } else if (randomMode == 2) {
            players.removeIf(player2 -> !levelName.equals(player2.getLevel().getName()));
        }
        if (players.isEmpty()) {
            admin.sendMessage("没有符合条件的玩家");
        }
        Player target = players.get(new Random().nextInt(players.size()));
        return target;
    }
}
