package cn.stevei5mc.serverhelper.common;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class BaseInfo {
    public static final String GH_URL = "[GITHUB] https://github.com/stevei5mc/ServerHelper";

    // 版本信息
    public static String getVersionInfo() {
        return "§bVersion§7:§a {project.version}\n§bBranch§7:§a {git.branch} §b[§a {git.commit.id} §b]";
    }

    // 配置文件信息
    public static final int configFileVersion = 1;
    public static final int languageFilesVersion = 1;
    public static final String settingsFilesPath = "Settings/";
    public static final String baseLanguagesFilesPath = "languages/base/";
    public static final String customLanguagesFilesPath = "languages/custom/";
    @Getter
    private static final List<String> languages = Arrays.asList(
            /*"bg_BG", "cs_CZ","da_DK","de_DE","el_GR","en_GB","en_US","es_ES","es_MX","fi_FI","fr_CA","fr_FR","hu_HU","id_ID","it_IT",
            "ja_JP","ko_KR","nb_NO","nl_NL","pl_PL","pt_BR","pt_PT","ru_RU","sk_SK","sv_SE","tr_TR","uk_UA","zh_CN","zh_TW"*/
    );
    @Getter
    private static final List<String> settings = Arrays.asList(/*"ban","kick","mute","warn"*/"banCommands");

    // 权限信息
    public static final String adminMainPermission = "serverhelper.admin";
    public static final String reloadPermission = "serverhelper.admin.reload";
    public static final String unbanCommandPermission = "serverhelper.admin.unban.commands";
    public static final String staffChatPermission = "serverhelper.admin.staffChat";
}