package cn.stevei5mc.serverhelper.waterdogpe.commands;

import cn.stevei5mc.serverhelper.waterdogpe.commands.base.CommandBase;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.LobbyServersInfo;
import cn.stevei5mc.serverhelper.waterdogpe.serverinfo.WServerInfo;
import cn.stevei5mc.serverhelper.waterdogpe.utils.LanguageApi;
import cn.stevei5mc.serverhelper.waterdogpe.utils.PluginI18n;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOverloadData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

public class LobbyCmd extends CommandBase {

    public LobbyCmd(String name, String description, String permission, String... aliases) {
        super(name, description, permission, aliases);
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        LanguageApi lang = PluginI18n.getBaseLang(sender);
        if (sender.isPlayer()) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length >= 1) {
                try {
                    int targetLobby = Integer.parseInt(args[0]);
                    int lobbyCount = LobbyServersInfo.getLobbyServerList().size();
                    if (targetLobby < 0 || targetLobby > lobbyCount) {
                        player.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-notFound"));
                        return true;
                    }
                    WServerInfo wServerInfo = LobbyServersInfo.getLobbyServerList().get(targetLobby - 1);
                    if (wServerInfo.isOnline()) {
                        if (wServerInfo.isFull()) {
                            player.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-isFull"));
                            return true;
                        }
                        player.connect(wServerInfo.getServerInfo());
                        return true;
                    }
                    player.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-isOffline"));
                }catch (NumberFormatException ignore) {
                    player.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-invalidInput"));
                }
            }else {
                if (LobbyServersInfo.getLobbyServerList().isEmpty()) {
                    sender.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-noAvailable"));
                    return true;
                }
                ServerInfo serverInfo = LobbyServersInfo.findServer(player, player.getServerInfo());
                if (serverInfo == null) {
                    sender.sendMessage(lang.translateString("serverTransfer-lobbyServer-message-noAvailable"));
                    return true;
                }
                player.connect(serverInfo);
            }
            return true;
        }
        sender.sendMessage(lang.translateString(""));
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
    public void sendHelp(CommandSender sender) {}
}
