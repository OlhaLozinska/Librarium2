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
import com.softserve.academy.service.CopyService;
import com.softserve.academy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private CopyService copyService;
    private static final Logger LOGGER = Logger.getLogger(UsersController.class);


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listBooks(ModelMap map) {
        map.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String listOrderedBooks(ModelMap map) {
        try {
            map.addAttribute("users", userService.getAllDebtors());
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", e.getMessage());
        }
        return "users";
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String listBook(ModelMap map, @PathVariable String id) {
        try {
            User user = userService.getUserById(id);
            map.addAttribute("user", user);
            map.addAttribute("copies", copyService.getAllCopiesByUser(user));
            map.addAttribute("daysOfUsingLibraryByUser", userService.getDaysOfUsingLibraryByUser(user));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", e.getMessage());
        }
        return "user";
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String orderBook(ModelMap map, @PathVariable String id,
                            @RequestParam("copy_id") String copyId, @RequestParam(value = "user_id") String userId
                          ) {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                boolean success = copyService.returnCopy(copyId, true, userId);
                LOGGER.debug("Ordering copy is successful: " + success);
            } catch (IllegalArgumentException e) {
                LOGGER.error(e.getMessage(), e);
                map.addAttribute("error", e.getMessage());
            }
        }

        return listBook(map, id);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView displayNewUserForm() {
        ModelAndView mv = new ModelAndView("addUser");
        mv.addObject("headerMessage", "Add User Details");
        mv.addObject("user", new User());

        return mv;
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@ModelAttribute User user, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/users");

        if (result.hasErrors()) {
            return new ModelAndView("error");
        }
        boolean isAdded = userService.saveUser(user);
        if (isAdded) {
            mv.addObject("message", "New user successfully added");
        } else {
            return new ModelAndView("error");
        }

        return mv;
    }
}
