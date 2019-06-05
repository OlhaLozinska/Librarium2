/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.dao.BookDao;
import com.softserve.academy.dao.CopyDao;
import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.dao.UserDao;
import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.Order;
import com.softserve.academy.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Copy service implementation.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
@Service
public class CopyServiceImpl implements CopyService {

    private static final Logger LOGGER = Logger.getLogger(CopyServiceImpl.class);
    @Autowired
    private CopyDao copyDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;


    /**
     * Returns all copies by book.
     *
     * @param book book.
     * @return list of matching copies.
     */
    @Override
    @Transactional
    public List<Copy> getAllCopiesByBook(Book book)
        throws IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book is null");
        }

        return copyDao.getAllCopiesWithOrdersCountByBookId(book.getId());
    }
    @Override
    public List<Copy> getAllCopiesByUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        } else if (user.getId() <= 0) {
            throw new IllegalArgumentException("User ID isn`t valid");
        }
        return copyDao.getAllCopiesWithOrdersCountByUserId(user.getId());
    }

    @Override
    public boolean returnCopy(String copyId, boolean toAvailable, String user_Id) throws IllegalArgumentException {
        if ((copyId == null)  || (copyId.isEmpty())) {
            throw new IllegalArgumentException("Id isn`t valid");
        }



        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);


        User reader;
        Copy copy;
        try {
            int copy_id = Integer.parseInt(copyId);
            int reader_id = Integer.parseInt(user_Id);


            reader = userDao.getUserById(reader_id);
            copy = copyDao.getCopyById(copy_id);

            if (reader == null || copy == null) {
                throw new IllegalArgumentException("Object doesn't exist");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("Id isn`t valid");
        }


        try {
            copyDao.changeCopyAvailability(copy, toAvailable, reader);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return true;
    }
}
