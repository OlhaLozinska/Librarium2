/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.entity.Book;

import java.util.List;

/**
 * Book service.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface BookService {

    /**
     * Returns list of all books.
     *
     * @return list of all books.
     */
    List<Book> getAllBooks();

    /**
     * Returns number of all books, which are publishing in period of Independence.
     *
     * @return number of books.
     */
    int getCountBooksPublishingInPeriodOfIndependence();

    /**
     * Returns book by book's ID.
     *
     * @param id book's ID
     * @return book.
     */
    Book getBookById(String id) throws IllegalArgumentException;

    /**
     * Returns ordered list of books in a certain period of dates.
     *
     * @param startDate      date of start period.
     * @param endDate        date of end period.
     * @param unpopularFirst sort by raring.
     * @return list of matching books.
     */
    List<Book> getOrderedBooksInPeriod(String startDate, String endDate, String unpopularFirst)
        throws IllegalArgumentException;

    /**
     * Returns average time of reading by book's ID.
     *
     * @param bookId book's ID
     * @return number of days.
     */
    int getAverageTimeOfReadingByBookId(int bookId);
}
