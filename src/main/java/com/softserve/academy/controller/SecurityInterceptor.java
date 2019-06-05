/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.controller;

import com.softserve.academy.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor class, handles security for request.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 02.06.2019
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    /**
     * Check authentication for request.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  interceptor handler
     * @return continue handling request or not.
     */
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
