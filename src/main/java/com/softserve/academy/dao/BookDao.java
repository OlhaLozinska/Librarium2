package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();

    Book getBookById(Integer id);
}
