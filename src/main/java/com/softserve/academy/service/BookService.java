package com.softserve.academy.service;

import com.softserve.academy.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    int getCountBooksPublishingInPeriodOfIndependence();
}
