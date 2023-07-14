package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class AuthFilter implements Filter {

    public void init(FilterConfig config){
        System.out.println("AccessFilter. Method init()");
    }

    public void destroy() {
        System.out.println("AccessFilter. Method destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if(session== null){
            response.sendRedirect("/login.jsp");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}