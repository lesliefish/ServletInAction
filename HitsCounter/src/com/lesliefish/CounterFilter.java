package com.lesliefish;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CounterFilter implements Filter {
    private int hitCount;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        hitCount = 0;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        hitCount++;
        System.out.println("Site visits count : " + hitCount);

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("cur request uri : " + request.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
