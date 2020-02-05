package com.coweii.filter;

import com.coweii.common.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends ZuulFilter {

    @Autowired
    JwtUtil jwtUtil;

    public JwtFilter() {
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //获取http请求头中的Authorization
        String header = request.getHeader("Authorization");
        // 判断请求头是否携带Bearer token
        try{
            if(header != null && header.startsWith("Bearer ")){
                String token = header.substring(7);
                Claims claims = jwtUtil.parseJwt(token);
                String roles = (String) claims.get("roles");
                if(roles != null && roles.equals("admin")){
                    /*requestContext.addZuulRequestHeader("Authorization1",header);*/
                    return null;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            requestContext.setSendZuulResponse(false);
        }

        requestContext.setSendZuulResponse(false);  //表示不允许通过
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
