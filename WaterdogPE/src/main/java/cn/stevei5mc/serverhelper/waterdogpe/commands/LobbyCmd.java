package cn.stevei5mc.serverhelper.waterdogpe.commands;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.WServerInfo;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOverloadData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public class LobbyCmd extends CommandBase {
    public LobbyCmd(String name, String description, String permission) {
        super(name, description, permission);
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (sender.isPlayer()) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length >= 1) {
                int targetLobby = Integer.parseInt(args[0]);
                int lobbyCount = LobbyServersInfo.getLobbyServerList().size();
                if (targetLobby < 0 || targetLobby > lobbyCount) {
                    player.sendMessage("§c大厅不存在！");
                    return true;
                }
                WServerInfo wServerInfo = LobbyServersInfo.getLobbyServerList().get(targetLobby - 1);
                if (wServerInfo.isOnline()) {
                    if (wServerInfo.isFull()) {
                        player.sendMessage("§c目标大厅已满员，请选择其他大厅");
                        return true;
                    }
                    player.connect(wServerInfo.getServerInfo());
                    return true;
                }
                player.sendMessage("§c目标大厅处于离线状态，请选择其他大厅");
            }else {
                if (LobbyServersInfo.getLobbyServerList().isEmpty()) {
                    sender.sendMessage("§c暂无可用的大厅服务器，请稍后再试！");
                    return true;
                }
                ServerInfo serverInfo = LobbyServersInfo.findServer(player, player.getServerInfo());
                if (serverInfo == null) {
                    sender.sendMessage("§c暂无可用的大厅服务器，请稍后再试！");
                    return true;
                }
                player.connect(serverInfo);
            }
            return true;
        }
        sender.sendMessage("§c该命令只能由玩家执行！");
        return true;
    }

    @Override
    protected CommandOverloadData[] buildCommandOverloads() {
        CommandParamData paramData = new CommandParamData();
        paramData.setName("lobby");
        paramData.setType(CommandParam.INT);

        return new CommandOverloadData[]{
            new CommandOverloadData(false, new CommandParamData[]{paramData})
        };
    }

    @Override
    public void sendHelp(CommandSender sender) {
    }
}
