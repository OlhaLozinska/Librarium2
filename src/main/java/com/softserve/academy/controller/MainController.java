package com.softserve.academy.controller;

import com.softserve.academy.service.BookService;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map)
    {
        map.addAttribute("BooksQuantityInIndependencePeriod",
            bookService.getCountBooksPublishingInPeriodOfIndependence());
        return "../index";
    }

}
