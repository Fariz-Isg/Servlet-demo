package com.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println("LoggingFilter: Request received for " + req.getRequestURI());

        chain.doFilter(request, response);

        System.out.println("LoggingFilter: Response sent for " + req.getRequestURI());
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}