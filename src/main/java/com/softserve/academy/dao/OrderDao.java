/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */
package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.User;

import java.util.Date;

public interface OrderDao {
    int getQuantityOfOrdersInAllPeriod();

    Integer getOrdersCountByBookId(Integer bookId);

    Integer getMaxOrdersCount();

    boolean orderCopy(User creator, User reader, Book book, Copy copy, Date deadlineDate);
}
