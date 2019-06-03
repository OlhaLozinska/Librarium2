/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.dao.BookDao;
import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.entity.Book;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Book service implementation.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    /**
     * Returns list of all books.
     *
     * @return list of all books.
     */
    @Override
    @Transactional
    public List<Book> getAllBooks() {
        List<Book> books = bookDao.getAllBooksWithOrdersCount();
        setBooksImageUrl(books);
        setBookRating(books);
        return books;
    }

    /**
     * Returns average time of reading by book's ID.
     *
     * @param bookId book's ID
     * @return number of days.
     */
    @Override
    @Transactional
    public int getAverageTimeOfReadingByBookId(int bookId) {
        return bookDao.getAverageTimeOfReadingByBookId(bookId);
    }

    /**
     * Returns book by book's ID.
     *
     * @param id book's ID
     * @return book.
     */
    @Override
    @Transactional
    public Book getBookById(String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Book ID is not valid");
        }

        int bookId;
        try {
            bookId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Book ID is not valid");
        }

        Book book = bookDao.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book with that id is not found");
        }

        book.setImageUrl("photo" + book.getId());
        book.setOrdersQuantity(orderDao.getOrdersCountByBookId(bookId));
        book.setRating(book.getOrdersQuantity() * 100 / orderDao.getMaxOrdersCount());
        return book;
    }

    private void setBooksImageUrl(final List<Book> books) {
        if (books == null) {
            return;
        }
        for (Book book : books) {
            book.setImageUrl("photo" + book.getId());
        }
    }

    private void setBookRating(final List<Book> books) {
        if ((books == null) || (books.isEmpty())) {
            return;
        }

        int maxOrderCount = Collections.max(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getOrdersQuantity() - o2.getOrdersQuantity();
            }
        }).getOrdersQuantity();

        for (Book book : books) {
            book.setRating(book.getOrdersQuantity() * 100 / maxOrderCount);
        }
    }

    /**
     * Returns ordered list of books in a certain period of dates.
     *
     * @param startDate      date of start period.
     * @param endDate        date of end period.
     * @param unpopularFirst sort by raring.
     * @return list of matching books.
     */
    @Override
    @Transactional
    public List<Book> getOrderedBooksInPeriod(String startDate, String endDate, String unpopularFirst)
        throws IllegalArgumentException {
        if ((startDate == null) || (endDate == null) ||
            (startDate.isEmpty()) || (endDate.isEmpty())) {
            throw new IllegalArgumentException("Date is null");
        }
        boolean sortAsc = unpopularFirst == null ? false : true;

        List<Book> orderedBooks;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            orderedBooks = bookDao.getOrderedListOfBooksInPeriod(formatter.parse(startDate),
                formatter.parse(endDate), sortAsc);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("Date is not valid");
        }

        setBooksImageUrl(orderedBooks);
        setBookRating(orderedBooks);
        return orderedBooks;
    }

    /**
     * Returns number of all books, which are publishing in period of Independence.
     *
     * @return number of books.
     */
    @Override
    @Transactional
    public int getCountBooksPublishingInPeriodOfIndependence() {
        return bookDao.getCountBooksPublishingInPeriodOfIndependence();
    }

}
