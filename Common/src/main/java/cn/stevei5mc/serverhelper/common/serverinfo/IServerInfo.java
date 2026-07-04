package cn.stevei5mc.serverhelper.common.serverinfo;

import java.net.InetSocketAddress;

public interface IServerInfo {

    String getName();

    default String getIP() {
        return this.getAddress().getHostName();
    }

    default int getPort() {
        return this.getAddress().getPort();
    }

    String getVersion();

    InetSocketAddress getAddress();

    int getProtocol();

    int getCurrentOnline();

    int getMaxOnline();

    boolean isOnline();

    default boolean isFull() {
        return getCurrentOnline() >= getMaxOnline();
    }

    void update(String[] data);
}