package com.softserve.academy.controller;

import com.softserve.academy.service.BookService;
import com.softserve.academy.service.OrdersService;
import com.softserve.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.AttributedString;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map) {
            map.addAttribute("BooksQuantityInIndependencePeriod",
                bookService.getCountBooksPublishingInPeriodOfIndependence());
            map.addAttribute("AverageReaderAge", userService.getUserStatisticAverageAge());
            map.addAttribute("QuantityOfOrdersInAllPeriod",
                ordersService.getQuantityOfOrdersInAllPeriod());
            map.addAttribute("AverageTimeOfUsingLibrary",
                userService.getUserAverageTimeOfUsingLibrary());
            return "../index";
        }
    }
}
