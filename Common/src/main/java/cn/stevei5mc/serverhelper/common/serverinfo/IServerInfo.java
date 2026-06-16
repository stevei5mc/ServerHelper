package cn.stevei5mc.serverhelper.common.serverinfo;

import java.net.InetSocketAddress;

public interface IServerInfo {

    String getName();

    String getIP();

    int getPort();

    String getVersion();

    InetSocketAddress getAddress();

    int getProtocol();

    int getCurrentOnline();

    int getMaxOnline();

    boolean isOnline();

    boolean isFull();

    void update(String[] data);
}