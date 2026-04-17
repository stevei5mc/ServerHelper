package check;

import cn.nukkit.utils.Config;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class checkFiles {

    @Test
    public void checkPluginDotYmlFile() {
        Config config = new Config(Config.YAML);
        config.load(this.getClass().getResourceAsStream("/plugin.yml"));
        ArrayList<String> permissions = new ArrayList<>(config.getSections("permissions").getAllMap().keySet());
        System.out.println("已在 plugin.yml 配置的权限节点=" + permissions);
        for (PermissionsInfo permissionInfo: PermissionsInfo.values()) {
            assertTrue(permissions.contains(permissionInfo.getPermission()), "无法寻找到指定权限节点=[" + permissionInfo.getPermission() + "]");
        }
    }
}