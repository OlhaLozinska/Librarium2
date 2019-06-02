package com.softserve.academy.controller;

import com.softserve.academy.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String servletPath = request.getServletPath();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && servletPath.equals("/login")) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        if (user == null) {
            if (servletPath.equals("/users")) {
                response.sendRedirect(request.getContextPath() + "/");
                return false;
            }
        }
        return true;
    }
}
