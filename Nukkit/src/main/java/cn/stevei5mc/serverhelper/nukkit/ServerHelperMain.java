package cn.stevei5mc.serverhelper.nukkit;

import cn.nukkit.plugin.PluginBase;

public class ServerHelperMain extends PluginBase {
    
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
}