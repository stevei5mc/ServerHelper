package cn.stevei5mc.serverhelper.nukkit;

import cn.lanink.gamecore.utils.Language;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.nukkit.command.admin.AdminCommand;
import cn.stevei5mc.serverhelper.nukkit.command.maincommand.ServerHelperMainCommand;
import cn.stevei5mc.serverhelper.nukkit.utils.GitVersionUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ServerHelperMain extends PluginBase {

    private static ServerHelperMain instance;
    private final List<String> languages = Arrays.asList(
            "bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"
    );
    private String defaultLanguage;
    private final HashMap<String, Language> languageBaseMap = new HashMap<>();
    private final List<String> settings = Arrays.asList("ban","kick","warn","mute");
    private Config config;
    /*private Config banSetting;
    private Config kickSetting;
    private Config warnSetting;
    private Config muteSetting;*/

    @Override
    public void onLoad() {
        instance = this;
//        this.saveConfigResources();
//        this.loadConfig();
    }

    @Override
    public void onEnable() {
        if (this.getServer().getPluginManager().getPlugin("MemoriesOfTime-GameCore") != null) {
            //loadBaseLanguage();
            this.getLogger().info(GitVersionUtil.getVersion());
            this.getLogger().info(GitVersionUtil.getCommitId());
            this.getLogger().info(GitVersionUtil.getBranch());
            this.getServer().getCommandMap().register("",new ServerHelperMainCommand());
            this.getServer().getCommandMap().register("admin",new AdminCommand("admin"));
            Server.getInstance().getScheduler().scheduleDelayedTask(this, () -> {
                this.getLogger().warning("§c警告! §c本插件为免费且开源的一款插件，如果你是付费获取到的那么你就被骗了");
                this.getLogger().info("§a开源链接和使用方法: §bhttps://github.com/stevei5mc/ServerHelper");
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
/*        for (String language : languages) {
            saveResource("language/base/"+language+".yml");
            saveResource("language/custom/"+language+".yml");
        }
        for (String setting : settings) {
            saveResource("Setting/"+setting+".yml");
        }*/
    }

    public void loadConfig() {
        this.config = new Config(this.getDataFolder()+"/config.yml",Config.YAML);
        /*this.banSetting = new Config(this.getDataFolder()+"/Setting/ban.yml",Config.YAML);
        this.kickSetting = new Config(this.getDataFolder()+"/Setting/kick.yml",Config.YAML);
        this.warnSetting = new Config(this.getDataFolder()+"/Setting/warn.yml",Config.YAML);
        this.muteSetting = new Config(this.getDataFolder()+"/Setting/mute.yml",Config.YAML);*/
    }

    public static ServerHelperMain getInstance() {
        return instance;
    }

    /*@Override
    public Config getConfig() {
        return config;
    }*/

    /*public Config getBanSetting() {
        return banSetting;
    }

    public Config getKickSetting() {
        return kickSetting;
    }

    public Config getWarnSetting() {
        return warnSetting;
    }

    public Config getMuteSetting() {
        return muteSetting;
    }*/

    //使用(有改动)https://github.com/MemoriesOfTime/CrystalWars/blob/master/src/main/java/cn/lanink/crystalwars/CrystalWars.java
    private void loadBaseLanguage() {
        this.defaultLanguage = this.config.getString("default_language", "zh_CN");
        if (!languages.contains(this.defaultLanguage)) {
            this.getLogger().error("Language" + this.defaultLanguage + "Not supported, will load Chinese!");
            this.defaultLanguage = "zh_CN";
        }
        for (String language : languages) {
            Config languageConfig = new Config(Config.YAML);
            languageConfig.load(this.getDataFolder() + "/language/" + language + ".yml");
            this.languageBaseMap.put(language, new Language(languageConfig));
        }
        this.getLogger().info(this.getBaseLang().translateString("plugin_language"));
    }
    //同上
    public Language getBaseLang() {
        return this.getBaseLang(null);
    }
    //同上
    public Language getBaseLang(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerLanguage = player.getLoginChainData().getLanguageCode();
            if (!this.languageBaseMap.containsKey(playerLanguage)) {
                playerLanguage = this.defaultLanguage;
            }
            return this.languageBaseMap.get(playerLanguage);
        }
        return this.languageBaseMap.get(this.defaultLanguage);
    }

    public String getMessagePrefix() {
        return config.getString("message_prefix","§b§ServerHelper §r§7>> ");
    }
}