package com.coweii.qa.filter;

import com.coweii.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        System.out.println("header: "+header);

        if(header != null && header.startsWith("Beared")){
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJwt(token);
            if(claims != null){
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims",claims);
                }
                if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims",claims);
                }
            }
        }
        return true;
    }
}
