package cn.stevei5mc.serverhelper.waterdogpe.commands;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.WServerInfo;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class LobbyCmd extends CommandBase {
    public LobbyCmd(String name, String description, String permission) {
        super(name, description, permission);
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (sender.isPlayer()) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length == 0) {
                if (LobbyServersInfo.getLobbyServerList().isEmpty()) {
                    sender.sendMessage("§c暂无可用的大厅服务器，请稍后再试！");
                    return true;
                }
                ServerInfo serverInfo = LobbyServersInfo.findServer(player, player.getServerInfo());
                player.connect(serverInfo);
                return true;
            }
        }
        sender.sendMessage("§c该命令只能由玩家执行！");
        return true;
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }
}
