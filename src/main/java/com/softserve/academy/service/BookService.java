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

public interface BookService {
    List<Book> getAllBooks();

    int getCountBooksPublishingInPeriodOfIndependence();

    Book getBookById(String id) throws IllegalArgumentException;

    List<Book> getOrderedBooksInPeriod(String startDate, String endDate, String unpopularFirst)
        throws IllegalArgumentException;


    int getAverageTimeOfReadingByBookId(int bookId);
}
