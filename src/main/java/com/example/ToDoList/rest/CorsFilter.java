package com.example.ToDoList.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
@Order(1)
public class CorsFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing my Cors filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("The filter itself");
        HttpServletResponse httpresponse = (HttpServletResponse)response;
    httpresponse.setHeader("Access-Control-Allow-Origin", "*");
    httpresponse.setHeader("Access-Control-Methods", "GET, POST, OPTIONS, PUT, DELETE");
    httpresponse.setHeader("Allow-Control-Headers", "Origin, Content-Type,Accept, Customheader, *");
    httpresponse.setHeader("Access-Control-Max-Age", "3600");
    httpresponse.setHeader("Allow-Control-Expose-Headers", "*");
    chain.doFilter(request, httpresponse);

    }

    @Override
    public void destroy() {
        log.info("Destroying my CORS filter");
    }
}
