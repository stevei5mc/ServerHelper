package cn.stevei5mc.serverhelper.waterdogpe;

import dev.waterdog.waterdogpe.plugin.Plugin;

public class ServerHelperMain extends Plugin {
    
    private static ServerHelperMain instance;
    
    public static ServerHelperMain getInstance() {
        return instance;
    }
    
    public void onEnable() {
        instance = this;
    }

    public void onDisable() {
        this.getLogger().info("停止运行");
    }

    public void loadConfig() {

    }
}