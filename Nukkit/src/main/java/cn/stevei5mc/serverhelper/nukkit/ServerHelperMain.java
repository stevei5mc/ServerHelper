package cn.stevei5mc.serverhelper.nukkit;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.Server;

public class ServerHelperMain extends PluginBase {
    
    private static ServerHelperMain instance;
    
    public static ServerHelperMain getInstance() {
        return instance;
    }
    public void onEnable() {
        instance = this;
        Server.getInstance().getScheduler().scheduleDelayedTask(this, () -> {
            this.getLogger().warning("§c警告! §c本插件为免费且开源的一款插件，如果你是付费获取到的那么你就被骗了");
            this.getLogger().info("§a开源链接和使用方法: §bhttps://github.com/stevei5mc/ServerHelper");
        },20);
    }

    public void onDisable() {
        this.getLogger().info("停止运行");
    }
}