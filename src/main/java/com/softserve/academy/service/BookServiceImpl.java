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
        List<Book> books = bookDao.getAllBooks();
        setBooksImageUrl(books);
        return books;
    }

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
}
