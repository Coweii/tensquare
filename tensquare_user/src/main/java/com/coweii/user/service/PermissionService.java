package com.coweii.user.service;

import com.coweii.user.dao.PermissionDao;
import com.coweii.user.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;

    public String[] getPathsByRoleId(String roleId){
        List<String> paths = new ArrayList();
        List<Permission> permissions = permissionDao.findByRoleId(roleId);
        for(Permission permission : permissions){
            paths.add(permission.getPath());
        }
        String[] strings = new String[paths.size()];
        return paths.toArray(strings);
    }
}
