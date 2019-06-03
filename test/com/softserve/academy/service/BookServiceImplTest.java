package com.softserve.academy.service;

import com.softserve.academy.dao.BookDaoImpl;
import com.softserve.academy.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookDaoImpl bookDao;

    @InjectMocks
    private BookServiceImpl bookService;

    /**
     * Test for @link bookService @method getAllBooks() when Book list is null
     * Expected result is:
     * method getAllBooksWithOrdersCount() invoked once
     */
    @Test
    public void getAllBooksListNull() {
        when(bookDao.getAllBooksWithOrdersCount()).thenReturn(null);
        bookService.getAllBooks();

        verify(bookDao, times(1)).getAllBooksWithOrdersCount();
    }

    /**
     * Test for @link bookService @method getAllBooks() for one book
     * Expected result is:
     * method getAllBooksWithOrdersCount() invoked once
     * and result list should contain Book with expected ImageUrl
     * and expected Rating
     */
    @Test
    public void getAllBooksOneBook() {
        Book testBook = new Book();
        testBook.setId(1);
        testBook.setOrdersQuantity(1);
        List<Book> books = new ArrayList<>();
        books.add(testBook);

        when(bookDao.getAllBooksWithOrdersCount()).thenReturn(books);
        List<Book> result = bookService.getAllBooks();

        verify(bookDao, times(1)).getAllBooksWithOrdersCount();
        assertEquals("ImageUrl should equals 'photo1'", "photo1",
            result.get(0).getImageUrl());
        assertEquals("Rating should equals 100", Integer.valueOf(100),
            result.get(0).getRating());
    }

    /**
     * Test for @link bookService @method getAllBooks() for two books
     * Expected result is:
     * method getAllBooksWithOrdersCount() invoked once
     * and result list should contain Book with expected ImageUrl
     * and expected Rating
     */
    @Test
    public void getAllBooksTwoBooks() {
        Book firstBook = new Book();
        firstBook.setId(1);
        firstBook.setOrdersQuantity(1);

        Book secondBook = new Book();
        secondBook.setId(2);
        secondBook.setOrdersQuantity(1);

        List<Book> books = new ArrayList<>();
        books.add(firstBook);
        books.add(secondBook);

        when(bookDao.getAllBooksWithOrdersCount()).thenReturn(books);
        List<Book> result = bookService.getAllBooks();

        verify(bookDao, times(1)).getAllBooksWithOrdersCount();
        assertEquals("ImageUrl for second book should equals 'photo2'", "photo2",
            result.get(1).getImageUrl());
        assertEquals("Rating for second book should equals 100", Integer.valueOf(100),
            result.get(1).getRating());
    }

    @Test
    public void getAverageTimeOfReadingByBookId() {
    }

    @Test
    public void getBookById() {
    }

    @Test
    public void getOrderedBooksInPeriod() {
    }

    @Test
    public void getCountBooksPublishingInPeriodOfIndependence() {
    }
}