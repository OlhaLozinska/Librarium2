/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */
package com.softserve.academy.dao;

import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getUserById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }


    @Override
    public int getUserStatisticAverageAge() {
        String hql = "select avg(year(current_date)-year(birthdayDate)) FROM User WHERE birthdayDate is not null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUsername(String userName) {
        String line = "from User where userName = :userName";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("userName", userName);
        List users = query.list();

        if (users.isEmpty()) {
            return null;
        } else {
            return (User) users.get(0);
        }
    }
    @Override
    public int getUserAverageTimeOfUsingLibrary() {
        String hql = "select avg(((year(current_date)*365)+(month(current_date)*12)+day(current_date))" +
            "-((year(createdAt)*365)+(month(createdAt)*12)+day(createdAt))) FROM User";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    @Override
    public int getUserAverageAgeByBookId(int bookId) {
        String hql = "select (avg(((year(current_date)*365)+(month(current_date)*12)+day(current_date))" +
            "-((year(U.birthdayDate)*365)+(month(U.birthdayDate)*12)+day(U.birthdayDate))))/365 " +
            "from  Order As O left join User AS U On U.id = O.reader.id and O.book.id=:bookId";

        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();

        return ((Double) results.get(0)).intValue();
    }

    @Override
    public int getUserAverageAgeByAuthor(Author author) {
        String hql = "select u from Author a left join a.books b " +
            "inner join b.orders o left join o.reader u where a.id=:authorId";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("authorId", author.getId());
        List results = query.list();
        Date date = new Date();
        int counter = 0;
        for (int i = 0; i < results.size(); i++) {
            User user = (User) results.get(i);
            counter += (date.getYear() - user.getBirthdayDate().getYear());
        }
        return counter / results.size();
    }
}
