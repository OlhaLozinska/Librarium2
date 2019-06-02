/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

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
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * Controller class, sets all routes for book pages.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 29.05.2019
 *
 */
@Controller
public class BooksController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyService copyService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = Logger.getLogger(BooksController.class);


    /**
     * Configure attributes for GET request to books page.
     *
     * @param map Request parameters map
     * @return view name.
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(ModelMap map) {
        map.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    /**
     * Configure attributes for POST request to books page.
     *
     * @param map Request parameters map
     * @param startDate start date period for sorting
     * @param endDate end date period for sorting
     * @param unpopularFirst order to sort
     * @return view name.
     */
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

    /**
     * Configure attributes for GET request to book page.
     *
     * @param map Request parameters map
     * @param id book id
     * @return view name.
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String listBook(ModelMap map, @PathVariable String id) {
        try {
            Book book = bookService.getBookById(id);
            map.addAttribute("book", book);
            map.addAttribute("copies", copyService.getAllCopiesByBook(book));
            map.addAttribute("averageTimeOfReading",
                bookService.getAverageTimeOfReadingByBookId(book.getId()));
            map.addAttribute("averageUserAgeByBook",
                userService.getUserAverageAgeByBookId(book.getId()));
            map.addAttribute("averageUserAgesForAuthors",
                userService.getUsersAverageAgesForAuthors(book.getAuthors()));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", e.getMessage());
        }

        map.addAttribute("users", userService.getAllUsers());

        return "book";
    }

    /**
     * Configure attributes for POST request to book page.
     *
     * @param map Request parameters map
     * @param id book id
     * @param copyId copy id - what copy is ordered
     * @param readerId reader id - who orders
     * @param bookId book id - what book is ordered
     * @return view name.
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.POST)
    public String orderBook(ModelMap map, @PathVariable String id,
                            @RequestParam("copyId") String copyId,
                            @RequestParam("readerId") String readerId,
                            @RequestParam(value = "bookId") String bookId) {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                boolean success = orderService.orderCopy(copyId, readerId, bookId, user);
                LOGGER.debug("Ordering copy is successful: " + success);
            } catch (IllegalArgumentException e) {
                LOGGER.error(e.getMessage(), e);
                map.addAttribute("error", e.getMessage());
            }
        }

        return listBook(map, id);
    }
}
