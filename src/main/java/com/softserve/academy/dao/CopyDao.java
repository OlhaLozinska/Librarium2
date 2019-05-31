/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */
package com.softserve.academy.dao;

import com.softserve.academy.entity.Copy;

import java.util.List;

public interface CopyDao {
    Copy getCopyById(Integer id);

    List<Copy> getAllCopiesWithOrdersCountByBookId(Integer bookId);
}
