package cn.stevei5mc.serverhelper.waterdogpe;

import dev.waterdog.waterdogpe.plugin.Plugin;

public class ServerHelperMain extends Plugin {
    
    private static ServerHelperMain instance;
    
    public static ServerHelperMain getInstance() {
        return instance;
    }
    
    public void onEnable() {
        this.getLogger().warn("§c警告! §c本插件为免费且开源的一款插件，如果你是付费获取到的那么你就被骗了");
        this.getLogger().info("§a开源链接和使用方法: §bhttps://github.com/stevei5mc/ServerHelper");
    }

    public void onDisable() {
        this.getLogger().info("停止运行");
    }

    public void loadConfig() {

    }
}