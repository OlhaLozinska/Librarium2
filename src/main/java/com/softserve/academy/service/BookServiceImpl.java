package com.softserve.academy.service;

import com.softserve.academy.dao.BookDao;
import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;


    @Override
    @Transactional
    public List<Book> getAllBooks() {
        List<Book> books = bookDao.getAllBooksWithOrdersCount();
        setBooksImageUrl(books);
        setBookRating(books);
        return books;
    }

    @Override
    @Transactional
    public int getAverageTimeOfReadingByBookId(int bookId) {
        return bookDao.getAverageTimeOfReadingByBookId(bookId);
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

    @Override
    @Transactional
    public int getCountBooksPublishingInPeriodOfIndependence() {
        return bookDao.getCountBooksPublishingInPeriodOfIndependence();
    };
}
