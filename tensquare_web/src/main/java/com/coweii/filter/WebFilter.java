package com.coweii.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

//@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";  // pre表示在请求前
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {  //是否开启此ZuulFilter
        return true;
    }

    //网站前台路由转发token（不转发会丢失）
    @Override
    public Object run() throws ZuulException {
        System.out.println("通过过滤器...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if(authorization != null && !"".equals(authorization)){
            System.out.println("Authorization不为空");
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;  //return null 直接表示通过
    }
}
