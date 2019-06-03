/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.entity.User;

/**
 * Order service.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */

public interface OrderService {

    /**
     * Returns orders count by book ID.
     *
     * @param copyId   copy ID.
     * @param readerId user ID.
     * @param bookId   book ID.
     * @param creator  user, which created the record.
     * @return condition.
     */
    boolean orderCopy(String copyId, String readerId, String bookId, User creator)
        throws IllegalArgumentException;

    /**
     * Returns quantity of orders in all period.
     *
     * @return quantity of orders.
     */
    int getQuantityOfOrdersInAllPeriod();
}
