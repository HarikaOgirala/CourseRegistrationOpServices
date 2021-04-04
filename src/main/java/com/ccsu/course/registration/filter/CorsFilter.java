package com.ccsu.course.registration.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
       if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

           HttpServletRequest req = (HttpServletRequest) request;
           HttpServletResponse res = (HttpServletResponse) response;

           res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
           res.setHeader("Vary", "Origin");
           res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
           res.setHeader("Access-Control-Max-Age", "3600");
           res.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
           res.addHeader("Access-Control-Expose-Headers", "xsrf-token");
           res.setHeader("Access-Control-Allow-Credentials","true");
           if ("OPTIONS".equals(req.getMethod())) {
               res.setStatus(HttpServletResponse.SC_OK);
           } else {
               filterChain.doFilter(request, response);
           }
       }
    }
}

