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

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Integer id);
    int getUserStatisticAverageAge();
    int getUserAverageTimeOfUsingLibrary();
    User getUserByUsername(String userName);

    int getUserAverageAgeByBookId(int bookId);

    int getUserAverageAgeByAuthor(Author author);


}
