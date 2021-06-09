package com.lab2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "XiaoFengFilter")
public class XiaoFengFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("XiaoFengFilter-->before chain");
        chain.doFilter(request, response);
        System.out.println("XiaoFengFilter-->after chain");
    }
}
