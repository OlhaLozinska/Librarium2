/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;

import java.util.List;

/**
 * Copy service.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
public interface CopyService {
    /**
     * Returns all copies by book.
     *
     * @param book book.
     * @return list of matching copies.
     */
    List<Copy> getAllCopiesByBook(Book book) throws IllegalArgumentException;
}
