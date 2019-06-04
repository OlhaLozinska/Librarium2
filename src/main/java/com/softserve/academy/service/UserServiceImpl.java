/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.dao.UserDao;
import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User service implementation.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * Returns all users.
     *
     * @return list of users.
     */
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Returns user by ID.
     *
     * @param id user ID.
     * @return user.
     */
    @Override
    @Transactional
    public User getUserById(Integer id) {
        if ((id == null) || (id <= 0)) {
            throw new IllegalArgumentException("User ID is not valid");
        }
        return userDao.getUserById(id);
    }

    /**
     * Returns user statistic average age.
     *
     * @return number of years.
     */
    @Override
    @Transactional
    public int getUserStatisticAverageAge() {
        return userDao.getUserStatisticAverageAge();
    }

    /**
     * Returns user average age by book ID.
     *
     * @param bookId book ID.
     * @return number of years.
     */
    @Override
    @Transactional
    public int getUserAverageAgeByBookId(int bookId) {
        return userDao.getUserAverageAgeByBookId(bookId);
    }

    /**
     * Returns user average time of using library.
     *
     * @return number of days.
     */
    @Override
    @Transactional
    public int getUserAverageTimeOfUsingLibrary() {
        return userDao.getUserAverageTimeOfUsingLibrary();
    }

    /**
     * Returns registered user.
     *
     * @param username username.
     * @param password password.
     * @return registered user.
     */
    @Override
    @Transactional
    public User getRegisteredUser(String username, String password) throws IllegalArgumentException {
        if ((username == null) || (password == null) ||
            (username.isEmpty()) || (password.isEmpty())) {
            throw new IllegalArgumentException("User credentials is empty");
        }
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with that username is not found");
        }
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new IllegalArgumentException("Password is not valid");
        }
    }

    /**
     * Returns list of user average ages for certain authors.
     *
     * @param authors list of authors.
     * @return list of average ages of users.
     */
    @Override
    @Transactional
    public List<Integer> getUsersAverageAgesForAuthors(List<Author> authors) throws IllegalArgumentException {
        if (authors == null) {
            throw new IllegalArgumentException("Authors list is null");
        }
        List<Integer> averageAges = new ArrayList<>();
        for (Author author : authors) {
            averageAges.add(userDao.getUserAverageAgeByAuthor(author));
        }
        return averageAges;
    }

    /**
     * Returns user average age by author.
     *
     * @param author author.
     * @return number of years.
     */
    @Override
    @Transactional
    public int getUserAverageAgeByAuthor(Author author) throws IllegalArgumentException {
        if (author == null) {
            throw new IllegalArgumentException("Author is null");
        }
        return userDao.getUserAverageAgeByAuthor(author);
    }

}
