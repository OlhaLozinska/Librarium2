package com.softserve.academy.service;

import com.softserve.academy.dao.BookDao;
import com.softserve.academy.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}
