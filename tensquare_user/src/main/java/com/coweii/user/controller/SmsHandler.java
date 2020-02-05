package com.coweii.user.controller;

import com.coweii.common.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsHandler {

    @Autowired
    SmsUtil smsUtil;

    @RabbitHandler
    public void smsDeal(Map map){
        String phoneNum = (String) map.get("phoneNum");
        String code = (String) map.get("code");
        System.out.println("从rabbitmq获取的code: "+code);
        smsUtil.sendSms(phoneNum,code);
    }
}
