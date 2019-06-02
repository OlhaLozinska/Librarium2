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
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class CopyDaoImpl implements CopyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public Copy getCopyById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(Copy.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Copy> getAllCopiesWithOrdersCountByBookId(Integer bookId) {
        String line = "select count(o.book), c from Copy c left join c.orders o where c.book.id = :bookId group by c.id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("bookId", bookId);
        Iterator iter = query.list().iterator();
        List<Copy> copies = new ArrayList<>();
        while (iter.hasNext()) {
            Object[] tuple = (Object[]) iter.next();
            Copy copy = (Copy) tuple[1];
            copy.setOrdersQuantity(((Long) tuple[0]).intValue());
            copies.add(copy);
        }
        return copies;
    }
}
