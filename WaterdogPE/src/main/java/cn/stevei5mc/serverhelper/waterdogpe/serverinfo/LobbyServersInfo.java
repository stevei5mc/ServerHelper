package cn.stevei5mc.serverhelper.waterdogpe.serverinfo;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyServersInfo {

    private static final ServerHelperMain main = ServerHelperMain.getInstance();
    @Getter
    private static final List<ServerInfo> lobbyServerList = new ArrayList<>();
    @Getter
    private static final HashMap<String, ServerInfo> lobbyServersMap = new HashMap<>();

    public static void loadLobbyServers() {
        lobbyServerList.clear();
        lobbyServersMap.clear();
        main.getProxy().getServers().forEach(serverInfo -> {
            if (main.getProxy().getConfiguration().getPriorities().contains(serverInfo.getServerName())) {
                lobbyServerList.add(serverInfo);
                lobbyServersMap.put(serverInfo.getServerName(), serverInfo);
            }
        });
        updateLobbyServersInfo();
    }

    public static void updateLobbyServersInfo() {
        main.getProxy().getScheduler().scheduleRepeating(() -> {
            main.getLogger().info(">>>>> : QWQ: 114514");
        }, main.getPrivateConfig().getInt("lobby-server.query-interval", 30) * 20, true);
    }

    public static ServerInfo findServer(ProxiedPlayer player) {
        return findServer(player, null);
    }

    public static ServerInfo findServer(ProxiedPlayer player, ServerInfo oldServer) {
        return lobbyServerList.get(0);
    }
}