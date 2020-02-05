package com.coweii.user.service;

import com.coweii.user.dao.RoleDao;
import com.coweii.user.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {
     @Autowired
     RoleDao roleDao;

     public List<Role> getAllRoles(){
         return roleDao.findAll();
     }
}
