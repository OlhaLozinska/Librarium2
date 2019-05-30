package com.softserve.academy.controller;

import com.softserve.academy.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
public class BooksController {
    @Autowired
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    private static final Logger LOGGER = Logger.getLogger(BooksController.class);

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(ModelMap map)
    {
        map.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String listBook(ModelMap map, @PathVariable String id)
    {
        try {
            map.addAttribute("book", bookService.getBookById(id));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return "book";
    }
}
