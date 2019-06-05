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
import com.softserve.academy.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
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
public class CopyDaoImpl implements CopyDao {
    /**
     * SessionFactory object
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Finds copy by copy ID.
     *
     * @param id copy ID
     * @return copy.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Copy getCopyById(Integer id)
    {
        Session session; // = this.sessionFactory.getSessionFactory().openSession();
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.get(Copy.class, id);
    }

    /**
     * Finds all copies with orders count by book ID.
     *
     * @param bookId book ID.
     * @return list of matching copies.
     */
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
    @SuppressWarnings("unchecked")
    @Override
    public List<Copy> getAllCopiesWithOrdersCountByUserId(Integer userId) {
        Session session = this.sessionFactory.getSessionFactory().openSession();
        String line = "select count(o.book), c from Copy c left join c.orders o left join o.reader as u where u.id = :users_id group by c.id";
        Query query = session.createQuery(line);
        query.setParameter("users_id", userId);
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

    @Override
    public boolean changeCopyAvailability(Copy copyId, boolean toAvailable, User reader) {
       // Session session = this.sessionFactory.getSessionFactory().openSession();
       // session.beginTransaction();
//        copyId.setAvailable(true);
  //      session.update(copyId);
        //System.out.println(copyId.getId());
        //session.getTransaction().commit();
      //  session.clear();
        Session session = this.sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Copy em = (Copy) session.load(Copy.class, copyId.getId());
        em.setAvailable(true);
        session.getTransaction().commit();
        session.close();

        return true;
    }
}
