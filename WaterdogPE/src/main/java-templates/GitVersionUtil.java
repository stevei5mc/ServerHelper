package cn.stevei5mc.serverhelper.waterdogpe.utils;

public class GitVersionUtil {

    public static final String getCommitId() {
        return "§bCommit id§7:§a ${git.commit.id.abbrev}";
    }

    public static final String getVersion() {
        return "§bVersion§7:§a ${git.build.version}";
    }

    public static final String getBranch() {
        return "§bBranch§7:§a ${git.branch}";
    }
}