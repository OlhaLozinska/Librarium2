package com.softserve.academy.controller;

import com.softserve.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUser(ModelMap map)
    {
        try {
            map.addAttribute("user", userService.getUserById(5));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "user";
    }
}
