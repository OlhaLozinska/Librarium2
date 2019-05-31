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

public interface OrderService {
    boolean orderCopy(String copyId, String readerId, String bookId, User creator)
        throws IllegalArgumentException;
}
