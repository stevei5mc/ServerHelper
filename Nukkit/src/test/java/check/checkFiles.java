package check;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.stevei5mc.serverhelper.common.baseinfo.PermissionsInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class checkFiles {

    @Test
    public void checkPluginDotYmlFile() {
        Config config = new Config(Config.YAML);
        config.load(this.getClass().getResourceAsStream("/plugin.yml"));
        ArrayList<String> permissionsList = new ArrayList<>(config.getSections("permissions").getAllMap().keySet());
        for (PermissionsInfo permissionInfo: PermissionsInfo.values()) {
            Map<String, Object> permissionMap = config.getSections("permissions").getSection(permissionInfo.getPermission()).getAllMap();
            assertTrue(permissionsList.contains(permissionInfo.getPermission()), "无法寻找到指定权限节点=[" + permissionInfo.getPermission() + "]");
            assertEquals("op", String.valueOf(permissionMap.get("default")), "权限节点的归属权出现错误");
            System.out.printf("权限节点=[%s]，归属=[%s]%n", permissionInfo.getPermission(), permissionMap.get("default"));
        }
    }
}