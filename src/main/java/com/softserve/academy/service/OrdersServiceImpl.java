/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.service;

import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public int getQuantityOfOrdersInAllPeriod() {
        return orderDao.getQuantityOfOrdersInAllPeriod();
    }

}
