/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.controller;

import com.softserve.academy.service.BookService;
import com.softserve.academy.service.OrderService;
import com.softserve.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Controller class, sets all routes for home page.
 *
 * @author Olha Lozinska
 * @version 1.0
 * @since 29.05.2019
 *
 */
@Controller
public class MainController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * Configure attributes for GET request to home page.
     *
     * @param map Request parameters map
     * @return view name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map) {
        map.addAttribute("BooksQuantityInIndependencePeriod",
            bookService.getCountBooksPublishingInPeriodOfIndependence());
        map.addAttribute("AverageReaderAge", userService.getUserStatisticAverageAge());
        map.addAttribute("QuantityOfOrdersInAllPeriod",
            orderService.getQuantityOfOrdersInAllPeriod());
        map.addAttribute("AverageTimeOfUsingLibrary",
            userService.getUserAverageTimeOfUsingLibrary());
        return "../index";
    }

}
