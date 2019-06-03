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

/**
 * Class which provide default implementation of CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    /**
     * SessionFactory objcet
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Finds orders count by book ID.
     *
     * @param bookId book ID.
     * @return number of orders.
     */
    @Override
    public Integer getOrdersCountByBookId(Integer bookId) {
        String line = "select count(book) from Order where book.id = :bookId";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("bookId", bookId);
        Long count = (Long) query.list().get(0);
        return count.intValue();
    }

    /**
     * Finds maximum orders count.
     *
     * @return maximum orders count.
     */
    @Override
    public Integer getMaxOrdersCount() {
        String line = "select count(book) from Order group by book";
        List ordersCount = this.sessionFactory.getCurrentSession().createQuery(line).list();
        return ((Long) Collections.max(ordersCount)).intValue();
    }

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

    /**
     * Finds quantity of orders in all period.
     *
     * @return quantity of orders.
     */
    @Override
    public int getQuantityOfOrdersInAllPeriod() {
        String hql = "select id FROM Order";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }

}
