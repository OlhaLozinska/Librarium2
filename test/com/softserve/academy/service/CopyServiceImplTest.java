package com.softserve.academy.service;

import com.softserve.academy.dao.CopyDaoImpl;
import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CopyServiceImplTest {

    @Mock
    private CopyDaoImpl copyDao;

    @InjectMocks
    private CopyServiceImpl copyService;

    /**
     * Test for @link copyService @method getAllCopiesByBook() when book is null
     * Expected result is: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAllCopiesByBookNull() {
        copyService.getAllCopiesByBook(null);
    }

    /**
     * Test for @link copyService @method getAllCopiesByBook() when id is negative
     * Expected result is: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAllCopiesByBookIdIsNegative() {
        Book book = new Book();
        book.setId(-2);
        copyService.getAllCopiesByBook(book);
    }

    /**
     * Test for @link copyService @method getAllCopiesByBook()
     * Expected result is:
     * method getAllCopiesByBook() invoked once
     */
    @Test
    public void getAllCopiesByBookOneBook() {
        Book book = new Book();
        book.setId(1);

        List<Copy> result = copyService.getAllCopiesByBook(book);
        verify(copyDao, times(1)).getAllCopiesWithOrdersCountByBookId(1);
    }
}
