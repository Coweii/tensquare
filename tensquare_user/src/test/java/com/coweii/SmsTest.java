package com.coweii;

import com.coweii.user.UserApplication;
import com.coweii.common.util.SmsUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class SmsTest {

    @Autowired
    SmsUtil smsUtil;

    @Value("${aliyun.sms.signName}")  //阿里云签名
    private String signName;

    @Value("${aliyun.sms.templateCode}")  //阿里云模板
    private String templateCode;

    //@Test
    public void test2(){
        smsUtil.sendSms("15602985101","452136");
    }
}
