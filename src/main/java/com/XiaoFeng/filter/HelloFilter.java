package com.XiaoFeng.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "HelloFilter", urlPatterns = "/hello")
public class HelloFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("i am in HelliFilter-->init()");
    }

    public void destroy() {
        System.out.println("i am in HelliFilter-->destroy()");
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("i am in HelliFilter-->doFilter()- before servlet-(request comes here)");
        chain.doFilter(request, response);
    }
}
