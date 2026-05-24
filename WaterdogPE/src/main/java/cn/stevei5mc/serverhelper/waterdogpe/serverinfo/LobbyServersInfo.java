package cn.stevei5mc.serverhelper.waterdogpe.serverinfo;

import cn.stevei5mc.serverhelper.waterdogpe.ServerHelperMain;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
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
    }
}