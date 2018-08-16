package com.dal.hrm_management.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luu Ngoc Lan on 15-Aug-18.
 */

public class PermissionManager {
    public static List<String> listPermissions = new ArrayList<>();

    public static boolean isPermited(List<String> listPermissions, String namePermission){
        for(String name: listPermissions) {
            if(name.trim().toLowerCase().equals(namePermission.trim().toLowerCase()))
                return true;
        }
        return false;
    }
}
