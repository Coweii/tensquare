package com.coweii.friend.filter;

import com.coweii.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component    //对请求添加token认证
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){  //认证成功
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJwt(token);
            if("user".equals(claims.get("roles"))){
                request.setAttribute("user_claims",claims);
            }
            if("admin".equals(claims.get("roles"))){
                request.setAttribute("admin_claims",claims);
            }
        }
        return true;
    }
}
