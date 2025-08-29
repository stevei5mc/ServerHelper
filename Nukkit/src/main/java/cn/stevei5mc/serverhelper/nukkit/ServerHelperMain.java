package cn.stevei5mc.serverhelper.nukkit;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.nukkit.commands.admin.AdminCommand;
import cn.stevei5mc.serverhelper.nukkit.commands.maincommand.ServerHelperMainCommand;
import cn.stevei5mc.serverhelper.nukkit.listener.PlayerListener;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ServerHelperMain extends PluginBase {
//这里被注释掉的代码都是暂时用不上的
    private static ServerHelperMain instance;
    private final List<String> languages = Arrays.asList(
            /*"bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"*/
    );
    private final List<String> settings = Arrays.asList(/*"ban","kick","warn","mute",*/"banCommands");
//    private Config config;
//    private Config banSetting;
//    private Config kickSetting;
//    private Config warnSetting;
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
            this.getLogger().info(getVersion());
            this.getLogger().info(getCommitId());
            this.getLogger().info(getBranch());
            this.getServer().getCommandMap().register("",new ServerHelperMainCommand());
            this.getServer().getCommandMap().register("",new AdminCommand("admin"));
            this.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
            Server.getInstance().getScheduler().scheduleDelayedTask(this, () -> {
                this.getLogger().warning("§c警告! §c本插件为免费且开源的，如果您付费获取获取的，则有可能被误导了");
                this.getLogger().info("§f[§aGITHUB§f] §bhttps://github.com/stevei5mc/ServerHelper");
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
        /*saveDefaultConfig();
        for (String language : languages) {
            saveResource("languages/base/"+language+".yml");
            saveResource("languages/custom/"+language+".yml");
        }*/
        for (String setting : settings) {
            saveResource("Settings/" +setting+".yml");
        }
    }

    public void loadConfig() {
//        this.config = new Config(this.getDataFolder()+"/config.yml",Config.YAML);
//        this.banSetting = new Config(this.getDataFolder()+"/Settings/ban.yml",Config.YAML);
//        this.kickSetting = new Config(this.getDataFolder()+"/Settings/kick.yml",Config.YAML);
//        this.warnSetting = new Config(this.getDataFolder()+ "/Settings/warn.yml",Config.YAML);
//        this.muteSetting = new Config(this.getDataFolder()+"/Settings/mute.yml",Config.YAML);
        this.banCommands = new Config(this.getDataFolder()+ "/Settings/banCommands.yml",Config.YAML);
    }

    public static ServerHelperMain getInstance() {
        return instance;
    }

    /*@Override
    public Config getConfig() {
        return config;
    }*/

    /*public String getMessagePrefix() {
        return config.getString("message_prefix","§b§ServerHelper §r§7>> ");
    }*/

    public String getCommitId() {
        return "§bCommit id§7:§a {git.commit.id.abbrev}";
    }

    public String getVersion() {
        return "§bVersion§7:§a "+getDescription().getVersion();
    }

    public String getBranch() {
        return "§bBranch§7:§a {git.branch}";
    }
}