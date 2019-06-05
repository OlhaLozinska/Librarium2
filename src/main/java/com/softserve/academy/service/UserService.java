/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;

import java.util.List;

/**
 * User service.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface UserService {
    /**
     * Returns all users.
     *
     * @return list of users.
     */
    List<User> getAllUsers();

    /**
     * Returns user by ID.
     *
     * @param id user ID.
     * @return user.
     */
    User getUserById(String id) throws IllegalArgumentException;

    /**
     * Returns user average time of using library.
     *
     * @return number of days.
     */
    int getUserAverageTimeOfUsingLibrary();

    /**
     * Returns user average age by book ID.
     *
     * @param bookId book ID.
     * @return number of years.
     */
    int getUserAverageAgeByBookId(int bookId);

    /**
     * Returns user average age by author.
     *
     * @param author author.
     * @return number of years.
     */
    int getUserAverageAgeByAuthor(Author author) throws IllegalArgumentException;

    /**
     * Returns list of user average ages for certain authors.
     *
     * @param authors list of authors.
     * @return list of average ages of users.
     */
    List<Integer> getUsersAverageAgesForAuthors(List<Author> authors) throws IllegalArgumentException;

    /**
     * Returns user statistic average age.
     *
     * @return number of years.
     */
    int getUserStatisticAverageAge();

    /**
     * Returns registered user.
     *
     * @param username username.
     * @param password password.
     * @return registered user.
     */
    User getRegisteredUser(String username, String password) throws IllegalArgumentException;

    List<User> getAllDebtors();

    boolean saveUser(User user);

    int getDaysOfUsingLibraryByUser (User user);
}
