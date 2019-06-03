/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.controller;

import com.softserve.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

/**
 *
 * Controller class, sets all routes for user pages.
 *
 * @author Andrii Dobrianskyi
 * @version 1.0
 * @since 29.05.2019
 *
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    /**
     * Configure attributes for GET request to user page.
     *
     * @param map Request parameters map
     * @return view name.
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUser(ModelMap map) {
        try {
            map.addAttribute("user", userService.getUserById(5));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "user";
    }
}
