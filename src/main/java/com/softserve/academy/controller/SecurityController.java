package com.softserve.academy.controller;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.User;
import com.softserve.academy.service.BookService;
import com.softserve.academy.service.CopyService;
import com.softserve.academy.service.OrderService;
import com.softserve.academy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(SecurityController.class);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap map)
    {
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getSession().invalidate();
        // TODO redirect to main controller to home page
        return "../index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("uname") String username,
                            @RequestParam("pwd") String password) {
        try {
            User user = userService.getRegisteredUser(username, password);
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession();
            session.setAttribute("user", user);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }

        // TODO redirect to main controller to home page
        return "../index";
    }
}
