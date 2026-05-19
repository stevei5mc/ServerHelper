package cn.stevei5mc.serverhelper.nukkit;

import cn.lanink.gamecore.utils.NukkitTypeUtils;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.common.BaseInfo;
import cn.stevei5mc.serverhelper.common.baseinfo.ResourcesFilesInfo;
import cn.stevei5mc.serverhelper.nukkit.commands.StaffChatCmd;
import cn.stevei5mc.serverhelper.nukkit.commands.admin.AdminCmd;
import cn.stevei5mc.serverhelper.nukkit.commands.maincmd.ServerHelperMainCmd;
import cn.stevei5mc.serverhelper.nukkit.listener.PlayerListener;
import cn.stevei5mc.serverhelper.nukkit.utils.PluginI18n;
import lombok.Getter;

public class ServerHelperMain extends PluginBase {
//这里被注释掉的代码都是暂时用不上的
    @Getter
    private static ServerHelperMain instance;
    @Getter
    private Config config;
    @Getter
    private Config privateConfig;
    public static final int privateConfigVersion = 1;
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
            this.getLogger().info(getPluginInfo().replace("\n", " §f| "));
            this.regCmd(new ServerHelperMainCmd());
            this.regCmd(new AdminCmd(config.getString("commands.name.admin", "admin")));
            if (this.privateConfig.getBoolean("waterdogPE-mode", false)) {
                this.regCmd(new StaffChatCmd(config.getString("commands.name.staffChat", "staffchat"), "ServerHelper Staff chat command"));
            }
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
        for (ResourcesFilesInfo fileInfo: ResourcesFilesInfo.values()) {
            saveResource(fileInfo.getJarPath());
        }
        saveResource("nukkit-private.yml");
        for (String language : BaseInfo.getLanguages()) {
            saveResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_BASE.getJarPath());
//            saveResource(ResourcesFilesInfo.PathsInfo.LANGUAGES_PRIVATE.getJarPath() + language+".yml");
        }
    }

    public void loadConfig() {
        this.config = new Config(this.getDataFolder() + ResourcesFilesInfo.DEFAULT_CONFIG.getDataPath(), Config.YAML);
        this.privateConfig = new Config(this.getDataFolder() + "/nukkit-private.yml", Config.YAML);
        this.banCommands = new Config(this.getDataFolder() + ResourcesFilesInfo.BAN_COMMANDS_CONFIG.getDataPath(), Config.YAML);
//        this.banSetting = new Config(this.getDataFolder()+"/Settings/ban.yml",Config.YAML);
//        this.kickSetting = new Config(this.getDataFolder()+"/Settings/kick.yml",Config.YAML);
//        this.warnSetting = new Config(this.getDataFolder()+ "/Settings/warn.yml",Config.YAML);
//        this.muteSetting = new Config(this.getDataFolder()+"/Settings/mute.yml",Config.YAML);
    }

    public String getMessagePrefix() {
        return config.getString("message_prefix","§b§lServerHelper §r§7>> ");
    }

    public String getPluginInfo() {
        return BaseInfo.getVersionInfo() + "\n§bNukkit type: §a" + NukkitTypeUtils.getNukkitType().name();
    }

    public void regCmd(Command command) {
        this.getServer().getCommandMap().register("", command);
    }
}