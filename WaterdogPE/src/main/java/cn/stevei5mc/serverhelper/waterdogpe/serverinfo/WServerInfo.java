package cn.stevei5mc.serverhelper.waterdogpe.serverinfo;

import cn.stevei5mc.serverhelper.common.serverinfo.IServerInfo;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import lombok.Getter;

import java.net.InetSocketAddress;

public class WServerInfo implements IServerInfo {

    private int maxOnline;
    private int protocol;
    private String version;

    @Getter
    private final ServerInfo serverInfo;

    public WServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public String getName() {
        return this.serverInfo.getServerName();
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public InetSocketAddress getAddress() {
        return this.serverInfo.getAddress();
    }

    @Override
    public int getProtocol() {
        return this.protocol;
    }

    @Override
    public int getCurrentOnline() {
        return this.serverInfo.getPlayers().size();
    }

    @Override
    public int getMaxOnline() {
        return this.maxOnline;
    }

    @Override
    public boolean isOnline() {
        return this.maxOnline >= 0;
    }

    @Override
    public void update(String[] data){
        if (data.length == 0) {
            maxOnline = -1;
            protocol = -1;
            version = "";
            return;
        }
        protocol = Integer.parseInt(data[2]);
        version = data[3];
        maxOnline = Integer.parseInt(data[5]);
    }
}