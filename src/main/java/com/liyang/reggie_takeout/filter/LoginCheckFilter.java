package com.liyang.reggie_takeout.filter;

import com.alibaba.fastjson.JSON;
import com.liyang.reggie_takeout.common.BaseContext;
import com.liyang.reggie_takeout.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * 拦截所有请求，判断是否登陆
 */


@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        boolean check = check(requestURI);
        log.info("请求路径：{}", requestURI);

        if (check) {
            log.info("本次请求放行");
            filterChain.doFilter(request,response);
            return;
        } else if (request.getSession().getAttribute("employee") != null) {
            Long empId = (Long) request.getSession().getAttribute("employee");
            log.info("用户已登陆，ID = {}", empId);
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);
            return;
        } else if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录，id为{}", request.getSession().getAttribute("user"));
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        } else {
            log.info("未登陆");
            response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        }
        return;
    }

    private boolean check(String requestURL) {
        String[] urls = new String[] {
                "/backend/**",
                "/front/**",
                "/employee/login",
                "/employee/logout",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        for (String url : urls) {
            boolean match = pathMatcher.match(url, requestURL);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
