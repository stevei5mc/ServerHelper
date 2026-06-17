package cn.stevei5mc.serverhelper.waterdogpe.serverinfo;

import cn.stevei5mc.serverhelper.common.utils.network.MotdMcServerUtil;
import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyServersInfo {

    private static final ServerHelperMain main = ServerHelperMain.getInstance();
    @Getter
    private static final List<WServerInfo> lobbyServerList = new ArrayList<>();

    public static void loadLobbyServers() {
        lobbyServerList.clear();
        main.getProxy().getServers().forEach(serverInfo -> {
            if (main.getProxy().getConfiguration().getPriorities().contains(serverInfo.getServerName())) {
                lobbyServerList.add(new WServerInfo(serverInfo));
            }
        });
        updateLobbyServersInfo();
    }

    public static void updateLobbyServersInfo() {
        main.getProxy().getScheduler().scheduleRepeating(() -> {
            lobbyServerList.forEach(wServerInfo -> {
                wServerInfo.update(MotdMcServerUtil.motdBeServer(wServerInfo.getAddress()));
            });
        }, main.getPrivateConfig().getInt("lobby-server.query-interval", 30) * 20, true);
    }

    public static ServerInfo findServer(ProxiedPlayer player) {
        return findServer(player, null);
    }

    public static ServerInfo findServer(ProxiedPlayer player, ServerInfo oldServer) {
        ArrayList<ServerInfo> lobbyServersInfo = new ArrayList<>();
        lobbyServerList.forEach(wServerInfo -> {
            if (!wServerInfo.getServerInfo().equals(oldServer) && wServerInfo.isOnline() && !wServerInfo.isFull() &&
                    wServerInfo.getCurrentOnline() < wServerInfo.getMaxOnline() - main.getPrivateConfig().getInt("lobby-server.reserve-count", 10)) {
                lobbyServersInfo.add(wServerInfo.getServerInfo());
            }
        });
        if (lobbyServersInfo.isEmpty()) {
            return null;
        }
        return lobbyServersInfo.get((new Random().nextInt(lobbyServersInfo.size())));
    }
}