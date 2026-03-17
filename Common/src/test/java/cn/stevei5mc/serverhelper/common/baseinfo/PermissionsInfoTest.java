package cn.stevei5mc.serverhelper.common.baseinfo;

import org.junit.jupiter.api.Test;

public class PermissionsInfoTest {

    @Test
    public void listAllPermissionInfo() {
        for (PermissionsInfo permission: PermissionsInfo.values()) {
            System.out.printf("Permission enum=[%s], node=[%s], type=[%s]\n", permission, permission.getPermission(),  permission.getType().getName());
        }
    }
}