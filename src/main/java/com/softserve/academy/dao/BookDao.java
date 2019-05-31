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

public interface BookDao {
    List<Book> getAllBooks();

    int getCountBooksPublishingInPeriodOfIndependence();
    Book getBookById(Integer id);

    List<Book> getAllBooksWithOrdersCount();

    List<Book> getOrderedListOfBooksInPeriod(Date startDate, Date endDate, boolean sortAsc);

    int getAverageTimeOfReadingByBookId(int bookId);
}
