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

/**
 * Establish contract for Order class CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface OrderDao {
    /**
     * Finds quantity of orders in all period.
     *
     * @return quantity of orders.
     */
    int getQuantityOfOrdersInAllPeriod();

    /**
     * Finds orders count by book ID.
     *
     * @param bookId book ID.
     * @return number of orders.
     */
    Integer getOrdersCountByBookId(Integer bookId);

    /**
     * Finds maximum orders count.
     *
     * @return maximum orders count.
     */
    Integer getMaxOrdersCount();

    /**
     * Finds orders count by book ID.
     *
     * @param creator      user, which created the record.
     * @param reader       user.
     * @param book         book.
     * @param copy         copy.
     * @param deadlineDate the end date for the expected return of the  book.
     * @return condition.
     */
    boolean orderCopy(User creator, User reader, Book book, Copy copy, Date deadlineDate);
}
