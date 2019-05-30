package com.softserve.academy.controller;

import com.softserve.academy.entity.Book;
import com.softserve.academy.service.BookService;
import com.softserve.academy.service.CopyService;
import com.softserve.academy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
public class BooksController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyService copyService;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(BooksController.class);


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(ModelMap map)
    {
        map.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String listOrderedBooks(ModelMap map, @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate,
                                   @RequestParam(value = "unpopularFirst", required = false) String unpopularFirst) {
        try {
            map.addAttribute("books", bookService.getOrderedBooksInPeriod(startDate, endDate, unpopularFirst));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", e.getMessage());
        }

        map.addAttribute("startDate", startDate);
        map.addAttribute("endDate", endDate);
        map.addAttribute("unpopularFirst", unpopularFirst);

        return "books";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String listBook(ModelMap map, @PathVariable String id)
    {
        try {
            Book book = bookService.getBookById(id);
            map.addAttribute("book", book);
            map.addAttribute("copies", copyService.getAllCopiesByBook(book));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", e.getMessage());
        }

        map.addAttribute("users", userService.getAllUsers());

        return "book";
    }
}
