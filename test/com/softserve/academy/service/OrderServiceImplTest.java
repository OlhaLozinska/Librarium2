package com.softserve.academy.service;

import com.softserve.academy.dao.BookDaoImpl;
import com.softserve.academy.dao.CopyDaoImpl;
import com.softserve.academy.dao.OrderDaoImpl;
import com.softserve.academy.dao.UserDaoImpl;
import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.User;
import com.softserve.academy.entity.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private CopyDaoImpl copyDao;
    @Mock
    private OrderDaoImpl orderDao;
    @Mock
    private UserDaoImpl userDao;
    @Mock
    private BookDaoImpl bookDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    /**
     * Test for @link orderService @method getQuantityOfOrdersInAllPeriod()
     * Expected result is:
     * method getQuantityOfOrdersInAllPeriod() invoked once
     */
    @Test
    public void getQuantityOfOrdersInAllPeriod() {
        orderService.getQuantityOfOrdersInAllPeriod();

        verify(orderDao, times(1))
            .getQuantityOfOrdersInAllPeriod();
    }

    /**
     * Test for @link orderService @method orderCopy() when ID is null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void orderCopyByIdNull() {
        orderService.orderCopy(null, null, null, null);
    }

    /**
     * Test for @link orderService @method orderCopy() when user type is USER
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void orderCopyByUserTypeIllegal() {
        User user = new User();
        user.setUserType(UserType.USER);

        orderService.orderCopy("copyId", "readerID", "bookId", user);
    }

    /**
     * Test for @link orderService @method orderCopy() when ID strings are not convertable in int
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void orderCopyByNotConvertableId() {
        User user = new User();
        user.setUserType(UserType.LIBRARIAN);

        orderService.orderCopy("copyId", "readerID", "bookId", user);
    }

    /**
     * Test for @link orderService @method orderCopy() when ID are not valid (<0)
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void orderCopyByNegativeId() {
        User user = new User();
        user.setUserType(UserType.LIBRARIAN);

        orderService.orderCopy("-1", "3", "-6", user);
    }

    /**
     * Test for @link orderService @method orderCopy() with right values
     * Expected result:
     * method userDao.getUserById() called once
     * method bookDao.getBookById() called once
     * method copyDao.getCopyById() called once
     * method orderDao.orderCopy() called once
     */
    @Test
    public void orderCopy() {
        User user = new User();
        user.setUserType(UserType.LIBRARIAN);

        Book testBook = new Book();
        Copy testCopy = new Copy();
        User testReader = new User();

        when(userDao.getUserById(any())).thenReturn(testReader);
        when(copyDao.getCopyById(any())).thenReturn(testCopy);
        when(bookDao.getBookById(any())).thenReturn(testBook);

        when(orderDao.orderCopy(any(), any(), any(), any(), any())).thenReturn(true);

        orderService.orderCopy("1", "1", "1", user);

        verify(userDao, times(1))
            .getUserById(any());
        verify(copyDao, times(1))
            .getCopyById(any());
        verify(bookDao, times(1))
            .getBookById(any());

        verify(orderDao, times(1))
            .orderCopy(any(), any(), any(), any(), any());
    }
}
