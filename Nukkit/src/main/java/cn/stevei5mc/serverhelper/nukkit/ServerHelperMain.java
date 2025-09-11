package cn.stevei5mc.serverhelper.nukkit;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.common.utils.BaseInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.admin.AdminCommand;
import cn.stevei5mc.serverhelper.nukkit.commands.maincommand.ServerHelperMainCommand;
import cn.stevei5mc.serverhelper.nukkit.listener.PlayerListener;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import lombok.Getter;

public class ServerHelperMain extends PluginBase {
//这里被注释掉的代码都是暂时用不上的
    private static ServerHelperMain instance;
    private Config config;
//    private Config banSetting;
//    private Config kickSetting;
    @Getter
    private Config warnSetting;
//    private Config muteSetting;
    @Getter
    private Config banCommands;

    @Override
    public void onLoad() {
        instance = this;
        this.saveConfigResources();
        this.loadConfig();
    }

    @Override
    public void onEnable() {
        if (this.getServer().getPluginManager().getPlugin("MemoriesOfTime-GameCore") != null) {
            PluginI18n.loadLanguages();
            this.getLogger().info(BaseInfo.VERSION);
            this.getLogger().info(BaseInfo.COMMIT_ID);
            this.getLogger().info(BaseInfo.BRANCH);
            this.getServer().getCommandMap().register("",new ServerHelperMainCommand());
            this.getServer().getCommandMap().register("",new AdminCommand("admin"));
            this.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
            Server.getInstance().getScheduler().scheduleDelayedTask(this, () -> {
                this.getLogger().warning("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
                this.getLogger().info(BaseInfo.GH_URL);
            },20);
        }else {
            //不存在则停止加载插件
            this.getLogger().warning("§c未检测到前置插件§aMemoriesOfTime-GameCore§c，请安装后再试!!!");
            this.getLogger().warning("§b下载地址: §ehttps://motci.cn/job/GameCore/");
            this.onDisable();
        }

    }

    @Override
    public void onDisable() {
        this.getLogger().info("已停止运行，感谢你的使用");
    }

    public void saveConfigResources() {
        saveDefaultConfig();
        for (String language : BaseInfo.getLanguages()) {
            saveResource(BaseInfo.baseLanguagesFilesPath + language+".yml");
            saveResource(BaseInfo.privateLanguagesFilesPath + language+".yml");
        }
        for (String setting : BaseInfo.getSettings()) {
            saveResource(BaseInfo.settingsFilesPath + setting+".yml");
        }
    }

    public void loadConfig() {
    String path = this.getDataFolder() + "/" + BaseInfo.settingsFilesPath;
//        this.config = new Config(this.getDataFolder()+"/config.yml",Config.YAML);
//        this.banSetting = new Config(this.getDataFolder()+"/Settings/ban.yml",Config.YAML);
//        this.kickSetting = new Config(this.getDataFolder()+"/Settings/kick.yml",Config.YAML);
//        this.warnSetting = new Config(this.getDataFolder()+ "/Settings/warn.yml",Config.YAML);
//        this.muteSetting = new Config(this.getDataFolder()+"/Settings/mute.yml",Config.YAML);
        this.banCommands = new Config(path + "banCommands.yml",Config.YAML);
    }

    public static ServerHelperMain getInstance() {
        return instance;
    }

    @Override
    public Config getConfig() {
        return config;
    }

    /*public String getMessagePrefix() {
        return config.getString("message_prefix","§b§ServerHelper §r§7>> ");
    }*/
}