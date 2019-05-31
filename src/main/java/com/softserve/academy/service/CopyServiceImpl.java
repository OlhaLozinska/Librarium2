/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */
package com.softserve.academy.service;

import com.softserve.academy.dao.CopyDao;
import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyDao copyDao;

    @Override
    @Transactional
    public List<Copy> getAllCopiesByBook(Book book)
        throws IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book is null");
        } else if (book.getId() <= 0) {
            throw new IllegalArgumentException("Book ID is not valid");
        }
        return copyDao.getAllCopiesWithOrdersCountByBookId(book.getId());
    }
}
