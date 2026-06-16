package cn.stevei5mc.serverhelper.common.utils.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MotdMcServerUtil {

    public static String[] motdBeServer(InetSocketAddress address) {
        return motdBeServer(address.getHostName(), address.getPort());
    }

    public static String[] motdBeServer(String host, int port) {
        final byte[] MOTD_DATA = new byte[]{1, 0, 0, 0, 0, 0, 3, 106, 7, 0, -1, -1, 0, -2, -2, -2, -2, -3, -3, -3, -3, 18, 52, 86, 120, -100, 116, 22, -68};

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(2000);
            DatagramPacket packet = new DatagramPacket(Arrays.copyOf(MOTD_DATA, 1024), 1024, InetAddress.getByName(host), port);
            socket.send(packet);
            socket.receive(packet);
            return new String(packet.getData(), 35, packet.getLength(), StandardCharsets.UTF_8).split(";");
        } catch (Throwable e) {
            if (socket != null) {
                socket.close();
            }
            return new String[0];
        }
    }
}