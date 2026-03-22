package cn.stevei5mc.serverhelper.common.baseinfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionsInfoTest {

    @Test
    public void checkAllPermissionInfo() {
        for (PermissionsInfo permission: PermissionsInfo.values()) {
            System.out.printf("Permission enum=[%s], node=[%s], type=[%s]\n", permission, permission.getPermission(),  permission.getType().getName());
            String permissionNode = permission.getPermission();
            boolean permissionRule = permissionNode != null && (permissionNode.startsWith(".") || permissionNode.endsWith(".") || permissionNode.contains("..") || permissionNode.contains("unknown"));
            assertFalse(permissionRule, "权限节点格式不是合法的, 权限节点=" + permissionNode);
        }
    }
}