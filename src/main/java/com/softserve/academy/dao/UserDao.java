/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.dao;

import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;

import java.util.List;

/**
 * Establish contract for User class CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface UserDao {
    /**
     * Finds all users.
     *
     * @return list of users.
     */
    List<User> getAllUsers();

    /**
     * Finds user by ID.
     *
     * @param id user ID.
     * @return user.
     */
    User getUserById(Integer id);

    /**
     * Finds user statistic average age.
     *
     * @return number of years.
     */
    int getUserStatisticAverageAge();

    /**
     * Finds user average time of using library.
     *
     * @return number of days.
     */
    int getUserAverageTimeOfUsingLibrary();

    /**
     * Finds user by username.
     *
     * @param userName username.
     * @return user.
     */
    User getUserByUsername(String userName);

    /**
     * Finds user average age by book ID.
     *
     * @param bookId book ID.
     * @return number of years.
     */
    int getUserAverageAgeByBookId(int bookId);

    /**
     * Finds user average age by author.
     *
     * @param author author.
     * @return number of years.
     */
    int getUserAverageAgeByAuthor(Author author);


}
