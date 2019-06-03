package com.softserve.academy.service;

import com.softserve.academy.dao.BookDaoImpl;
import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.dao.OrderDaoImpl;
import com.softserve.academy.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookDaoImpl bookDao;
    @Mock
    private OrderDaoImpl orderDao;

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

    /**
     * Test for @link bookService @method getAverageTimeOfReadingByBookId()
     * Expected result is:
     * method getAverageTimeOfReadingByBookId() invoked once
     */
    @Test
    public void getAverageTimeOfReadingByBookId() {
        int result = bookService.getAverageTimeOfReadingByBookId(1);
        verify(bookDao, times(1)).getAverageTimeOfReadingByBookId(1);
    }

    /**
     * Test for @link bookService @method getBookById() when ID is null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getBookByIdNull() {
        bookService.getBookById(null);
    }

    /**
     * Test for @link bookService @method getBookById() when ID is wrong format
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getBookByIdWrong() {
        bookService.getBookById("x");
    }

    /**
     * Test for @link bookService @method getBookById() when ID is not found
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getBookByIdNotFound() {
        bookService.getBookById("100");
    }

    /**
     * Test for @link bookService @method getBookById()
     * Expected result:
     * method getBookById() invoked once
     */
    @Test
    public void getBookByIdOneBook() {
        Book testBook = new Book();
        testBook.setId(1);
        testBook.setOrdersQuantity(1);

        when(bookDao.getBookById(1)).thenReturn(testBook);
        when(orderDao.getMaxOrdersCount()).thenReturn(1);

        Book result = bookService.getBookById(String.valueOf(1));

        verify(bookDao, times(1)).getBookById(1);
        assertEquals("ImageUrl should equals 'photo1'", "photo1",
            result.getImageUrl());
        assertEquals("Rating should equals 0", Integer.valueOf(0),
            result.getRating());
        assertEquals("Orders Quantity should equals 0", Integer.valueOf(0),
            result.getOrdersQuantity());
    }

    /**
     * Test for @link bookService @method getOrderedBooksInPeriod() when period is null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getOrderedBooksInPeriodNull() {
        bookService.getOrderedBooksInPeriod(null, null, "unpopularFirst");
    }

    /**
     * Test for @link bookService @method getOrderedBooksInPeriod() when period is empty
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getOrderedBooksInPeriodEmpty() {
        bookService.getOrderedBooksInPeriod("", "", "unpopularFirst");
    }

    /**
     * Test for @link bookService @method getOrderedBooksInPeriod() when date is wrong
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getOrderedBooksInPeriodWrongDate() {
        bookService.getOrderedBooksInPeriod("2018-001", "2018-01-01", "unpopularFirst");
    }

    /**
     * Test for @link bookService @method getOrderedBooksInPeriod()
     * Expected result: rating of book1 > rating of book2
     */
    @Test
    public void getOrderedBooksInPeriodBooleanNull() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setOrdersQuantity(3);
        Book book2 = new Book();
        book2.setId(2);
        book2.setOrdersQuantity(2);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookDao.getOrderedListOfBooksInPeriod(any(), any(), anyBoolean())).thenReturn(books);

        List<Book> result = bookService.getOrderedBooksInPeriod("2018-06-19", "2018-06-20",
            null);

        assertTrue(result.get(0).getRating() > result.get(1).getRating());
    }


    /**
     * Test for @link bookService @method getCountBooksPublishingInPeriodOfIndependence()
     * Expected result is:
     * method getAllBooksWithOrdersCount() invoked once
     */
    @Test
    public void getCountBooksPublishingInPeriodOfIndependence() {
        int result = bookService.getCountBooksPublishingInPeriodOfIndependence();
        verify(bookDao, times(1))
            .getCountBooksPublishingInPeriodOfIndependence();
    }
}