package com.softserve.academy.service;

import com.softserve.academy.dao.UserDaoImpl;
import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;
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
public class UserServiceImplTest {

    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private UserServiceImpl userService;

    /**
     * Test for @link userService @method getAllUsers()
     * Expected result is:
     * method getAllUsers() invoked once
     */
    @Test
    public void getAllUsers() {
        userService.getAllUsers();
        verify(userDao, times(1))
            .getAllUsers();
    }

    /**
     * Test for @link userService @method getUserById() when id null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getUserByIdNull() {
        userService.getUserById(null);
    }

    /**
     * Test for @link userService @method getUserById()
     * Expected result is:
     * method getUserById() invoked once
     */
    @Test
    public void getUserById() {
        userService.getUserById(1);
        verify(userDao, times(1))
            .getUserById(1);
    }

    /**
     * Test for @link userService @method getUserStatisticAverageAge()
     * Expected result is:
     * method getUserStatisticAverageAge() invoked once
     */
    @Test
    public void getUserStatisticAverageAge() {
        userService.getUserStatisticAverageAge();
        verify(userDao, times(1))
            .getUserStatisticAverageAge();
    }

    /**
     * Test for @link userService @method getUserAverageAgeByBookId()
     * Expected result is:
     * method getUserAverageAgeByBookId() invoked once
     */
    @Test
    public void getUserAverageAgeByBookId() {
        userService.getUserAverageAgeByBookId(1);
        verify(userDao, times(1))
            .getUserAverageAgeByBookId(1);
    }

    /**
     * Test for @link userService @method getUserAverageTimeOfUsingLibrary()
     * Expected result is:
     * method getUserAverageTimeOfUsingLibrary() invoked once
     */
    @Test
    public void getUserAverageTimeOfUsingLibrary() {
        userService.getUserAverageTimeOfUsingLibrary();
        verify(userDao, times(1))
            .getUserAverageTimeOfUsingLibrary();
    }

    /**
     * Test for @link userService @method getUserAverageAgeByAuthor()
     * Expected result is:
     * method getUserAverageAgeByAuthor() invoked once
     */
    @Test
    public void getUserAverageAgeByAuthor() {
        Author testAuthor = new Author();

        when(userDao.getUserAverageAgeByAuthor(any())).thenReturn(1);

        userService.getUserAverageAgeByAuthor(testAuthor);
        verify(userDao, times(1))
            .getUserAverageAgeByAuthor(testAuthor);
    }

    /**
     * Test for @link userService @method getUserAverageAgeByAuthor() when author is null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getUserAverageAgeByAuthorNull() {
        userService.getUserAverageAgeByAuthor(null);
    }

    /**
     * Test for @link userService @method getUsersAverageAgesForAuthors()
     * Expected result is:
     * method getUsersAverageAgesForAuthors() invoked once
     */
    @Test
    public void getUsersAverageAgesForAuthors() {
        Author testAuthor = new Author();
        List<Author> testList = new ArrayList<>();
        testList.add(testAuthor);
        testList.add(testAuthor);
        testList.add(testAuthor);

        when(userDao.getUserAverageAgeByAuthor(any())).thenReturn(1);

        userService.getUsersAverageAgesForAuthors(testList);
        verify(userDao, times(testList.size()))
            .getUserAverageAgeByAuthor(any());
    }

    /**
     * Test for @link userService @method getUsersAverageAgesForAuthors() when list is null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getUsersAverageAgesForAuthorsNull() {
        userService.getUsersAverageAgesForAuthors(null);
    }

    /**
     * Test for @link userService @method getRegisteredUser() when credentials are null
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getRegisteredUserCredentialsNull() {
        userService.getRegisteredUser(null, null);
    }

    /**
     * Test for @link userService @method getRegisteredUser() when user is not found (null)
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getRegisteredUserNotFound() {
        when(userDao.getUserByUsername(any())).thenReturn(null);

        userService.getRegisteredUser("username", "password");

        verify(userDao, times(1))
            .getUserByUsername(any());
    }

    /**
     * Test for @link userService @method getRegisteredUser() when password is incorrect
     * Expected result: IllegalArgumentException to be throw
     */
    @Test(expected = IllegalArgumentException.class)
    public void getRegisteredUserIncorrectPassword() {
        User testUser = new User();
        testUser.setPassword("123");

        when(userDao.getUserByUsername(any())).thenReturn(testUser);

        userService.getRegisteredUser("username", "password");

        verify(userDao, times(1))
            .getUserByUsername(any());
    }

    /**
     * Test for @link userService @method getRegisteredUser() when password is correct
     * Expected result is:
     * returned found user
     */
    @Test
    public void getRegisteredUser() {
        User testUser = new User();
        testUser.setPassword("123");

        when(userDao.getUserByUsername(any())).thenReturn(testUser);

        User user = userService.getRegisteredUser("username", "123");

        assertEquals(testUser, user);
    }
}
