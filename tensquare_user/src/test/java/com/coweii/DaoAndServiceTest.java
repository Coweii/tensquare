package com.coweii;

import com.coweii.user.UserApplication;
import com.coweii.user.dao.PermissionDao;
import com.coweii.user.dao.RoleDao;
import com.coweii.user.dao.UserDao;
import com.coweii.user.pojo.Permission;
import com.coweii.user.pojo.Role;
import com.coweii.user.pojo.User;
import com.coweii.user.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class DaoAndServiceTest {
    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserDao userDao;

    @Test
    public void test11(){
        userDao.updateFollowcount(1,"123");
    }

    @Test
    public void test1(){
        List<Role> roles =  roleDao.findRolesById("1217700154845564928");
        for(Role role : roles){
            System.out.println(role);
            System.out.println(role.getRoleName());
        }
    }

    @Test
    public void test2(){
        List<Permission> list = permissionDao.findByRoleId("2");
        System.out.println(list);
    }

    @Test
    public void test3(){
        String[] strings = permissionService.getPathsByRoleId("2");
        for(String s : strings){
            System.out.println(s);
        }
    }

    @Test
    public void test4(){
        User user = userDao.findByMobile("15602985101");
        System.out.println(user==null);

    }
}
