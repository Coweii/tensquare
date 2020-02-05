package com.coweii.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "jwt-config")
public class JwtUtil {
    String key;  //密钥
    String issuer; //发布者
    long ttl;   //过期事件

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String creatJwt(String id, String subject, String roles){
        long now = System.currentTimeMillis();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date())
                .setSubject(subject)
                .claim("roles",roles);
        if(ttl > 0){
            jwtBuilder.setExpiration(new Date(now+ttl));
        }
        if(issuer != null && issuer != ""){
            jwtBuilder.setIssuer(issuer);
        }
        jwtBuilder.signWith(SignatureAlgorithm.HS256,key);

        return jwtBuilder.compact();
    }

    public Claims parseJwt(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
