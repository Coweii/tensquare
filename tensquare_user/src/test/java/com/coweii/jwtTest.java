package com.coweii;

import com.coweii.user.UserApplication;
import com.coweii.common.util.JwtUtil;
import io.jsonwebtoken.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class jwtTest {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    public void testUtil2(){
        System.out.println(jwtUtil.getKey());
        System.out.println(jwtUtil.getIssuer());
        System.out.println(jwtUtil.getTtl());
    }

    @Test
    public void testUtil(){
        String token = jwtUtil.creatJwt("123","吕天林","admin");
        System.out.println(token);
        Claims claims = jwtUtil.parseJwt(token);
        System.out.println(claims);
    }


    @Test
    public void test(){
        JwtBuilder builder = Jwts.builder();

        long now = System.currentTimeMillis();
        long expire = now + 1000*60;

        builder.setId("111")
                .setIssuedAt(new Date(now))
                .setIssuer("Coweii")
                .setSubject("小林")
                .setExpiration(new Date(expire)) //设置过期时间
                .signWith(SignatureAlgorithm.HS256,"Coweii123@");

        System.out.println(builder.compact());
    }

    @Test
    public void test2(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJpYXQiOjE1ODAyMTIxNDQsImlzcyI6IkNvd2VpaSIsInN1YiI6IuWwj-aelyJ9.g4FXKHS5jw7EK1dJUhRMVsP2ZOKNWHVP17qwu8LA03U";
        String token2 = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJpYXQiOjE1ODAyMTI1ODcsImlzcyI6IkNvd2VpaSIsInN1YiI6IuWwj-aelyIsImV4cCI6MTU4MDIxMjY0N30.7xdWJcZV4fJyCQ8vhrAoS5eX_SAgkC42p-1hpQPrGEw";
        String token3 = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJpYXQiOjE1ODAyMTI4NTcsImlzcyI6IkNvd2VpaSIsInN1YiI6IuWwj-aelyIsImV4cCI6MTU4MDIxMjkxNywibmFtZSI6IkNvd2VpaSIsInBpY3R1cmUiOiLlkLnpm6oucG5nIn0.dqRCR8JTJoiDWcKioh2ZhT1vLEzmFO4T7igbYqli3rI";

        Claims claims = Jwts.parser().setSigningKey("Coweii123@").parseClaimsJws(token3).getBody();
        System.out.println(claims);
    }

    @Test
    public void test3(){
        JwtBuilder builder = Jwts.builder();

        long now = System.currentTimeMillis();
        long expire = now + 1000*60;

        builder.setId("111")
                .setIssuedAt(new Date(now))
                .setIssuer("Coweii")
                .setSubject("小林")
                .setExpiration(new Date(expire)) //设置过期时间
                .claim("name","Coweii") //自定义claim
                .claim("picture","吹雪.png")  //自定义claim
                .signWith(SignatureAlgorithm.HS256,"Coweii123@");

        System.out.println(builder.compact());
    }
}
