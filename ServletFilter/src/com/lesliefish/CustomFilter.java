package com.lesliefish;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site = filterConfig.getInitParameter("Site");

        // 输出初始化参数
        System.out.println("网站名称: " + site);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("此处可以过滤请求url");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("本次请求的uri ：" + request.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
