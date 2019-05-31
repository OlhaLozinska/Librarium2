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

/**
 * Establish contract for Copy class CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface CopyDao {
    /**
     * Finds copy by copy ID.
     *
     * @param id copy ID
     * @return copy.
     */
    Copy getCopyById(Integer id);

    /**
     * Finds all copies with orders count by book ID.
     *
     * @param bookId book ID.
     * @return list of matching copies.
     */
    List<Copy> getAllCopiesWithOrdersCountByBookId(Integer bookId);
}
