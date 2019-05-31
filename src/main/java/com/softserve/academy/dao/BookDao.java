/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;

import java.util.Date;
import java.util.List;

/**
 * Establish contract for Book class CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface BookDao {
    /**
     * Finds count of books publishing in period of Independence.
     *
     * @return number of books.
     */
    int getCountBooksPublishingInPeriodOfIndependence();

    /**
     * Finds book by book's ID.
     *
     * @param id book's ID
     * @return book.
     */
    Book getBookById(Integer id);

    /**
     * Finds all books with orders count.
     *
     * @return list of matching books.
     */
    List<Book> getAllBooksWithOrdersCount();

    /**
     * Finds ordered list of books in a certain period of dates.
     *
     * @param startDate date of start period.
     * @param endDate   date of end period.
     * @param sortAsc   sort by ascending.
     * @return list of matching books.
     */
    List<Book> getOrderedListOfBooksInPeriod(Date startDate, Date endDate, boolean sortAsc);

    /**
     * Finds average time of reading by book's ID.
     *
     * @param bookId book's ID
     * @return number of days.
     */
    int getAverageTimeOfReadingByBookId(int bookId);
}
