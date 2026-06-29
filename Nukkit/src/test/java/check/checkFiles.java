package check;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import cn.stevei5mc.serverhelper.nukkit.ServerHelperMain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class checkFiles {

    @Test
    public void checkPluginDotYmlFile() {
        Config config = new Config(Config.YAML);
        config.load(this.getClass().getResourceAsStream("/plugin.yml"));
        ConfigSection configSection = config.getSections("permissions");
        for (PermissionsInfo permissionInfo: PermissionsInfo.values()) {
            if (permissionInfo.equals(PermissionsInfo.PLAYER_LOBBY)) {
                continue;
            }
            Map<String, Object> permissionMap = configSection.getSection(permissionInfo.getPermission()).getAllMap();
            assertTrue(configSection.exists(permissionInfo.getPermission()), "无法寻找到指定权限节点=[" + permissionInfo.getPermission() + "]");
            System.out.printf("权限节点=[%s]，归属=[%s]%n", permissionInfo.getPermission(), permissionMap.get("default"));
            assertEquals("op", String.valueOf(permissionMap.get("default")), "权限节点的归属权出现错误");

        }
    }

    @Test
    public void checkPrivateConfig() {
        Config config = new Config(Config.YAML);
        config.load(this.getClass().getResourceAsStream("/nukkit-private.yml"));
        assertTrue(config.exists("version"), "无法找到配置文件的版本信息");
        assertEquals(ServerHelperMain.privateConfigVersion, config.getInt("version"), "配置文件的版本出现错误");
    }
}