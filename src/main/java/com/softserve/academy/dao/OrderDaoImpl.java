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
import com.softserve.academy.entity.Order;
import com.softserve.academy.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Integer getOrdersCountByBookId(Integer bookId) {
        String line = "select count(book) from Order where book.id = :bookId";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("bookId", bookId);
        Long count = (Long) query.list().get(0);
        return count.intValue();
    }

    @Override
    public Integer getMaxOrdersCount() {
        String line = "select count(book) from Order group by book";
        List ordersCount = this.sessionFactory.getCurrentSession().createQuery(line).list();
        return ((Long) Collections.max(ordersCount)).intValue();
    }

    @Override
    public boolean orderCopy(User creator, User reader, Book book, Copy copy, Date deadlineDate) {
        Session session = this.sessionFactory.getCurrentSession();

        Date nowDate = new Date();
        Order order = new Order();
        order.setCreatedAt(nowDate);
        order.setCreator(creator);
        order.setReader(reader);
        order.setBook(book);
        order.setCopy(copy);
        order.setTakeDate(nowDate);
        order.setDeadlineDate(deadlineDate);

        session.save(order);

        copy.setAvailable(false);
        session.update(copy);

        return true;
    }

    @Override
    public int getQuantityOfOrdersInAllPeriod() {
        String hql = "select id FROM Order";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }

}
