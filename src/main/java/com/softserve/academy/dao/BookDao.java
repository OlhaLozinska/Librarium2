package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookDao {
    Book getBookById(Integer id);

    List<Book> getAllBooksWithOrdersCount();

    List<Book> getOrderedListOfBooksInPeriod(Date startDate, Date endDate, boolean sortAsc);
}
