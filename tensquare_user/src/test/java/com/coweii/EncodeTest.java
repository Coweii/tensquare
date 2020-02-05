package com.coweii;

import com.coweii.user.UserApplication;
import com.coweii.user.dao.UserDao;
import com.coweii.user.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class EncodeTest {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        System.out.println(encoder.matches("868179","$2a$10$jw6i6ghHwHDL8TlN9vFiPOyu6sfWPXQecy9IR9whZuX3KTRcP/kPe"));
    }

    @Test
    public void test2(){
        User user = userDao.findByMobile("15602985101");
        System.out.println(encoder.matches("868179",user.getPassword()));
    }

    @Test
    public void test3(){
        System.out.println(encoder.encode("868179"));
    }

}
