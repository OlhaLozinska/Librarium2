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

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
public class UserDaoImpl implements UserDao {
    /**
     * SessionFactory objcet
     */
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    /**
     * Finds user by ID.
     *
     * @param id user ID.
     * @return user.
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    /**
     * Finds user statistic average age.
     *
     * @return number of years.
     */
    @Override
    public int getUserStatisticAverageAge() {
        String hql = "select avg(year(current_date)-year(birthdayDate)) FROM User WHERE birthdayDate is not null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    /**
     * Finds user by username.
     *
     * @param userName username.
     * @return user.
     */
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

    /**
     * Finds user average time of using library.
     *
     * @return number of days.
     */
    /*@Override
    public int getUserAverageTimeOfUsingLibrary() {
        String hql = "select avg(((year(current_date)*365)+(month(current_date)*30)+day(current_date))" +
            "-((year(createdAt)*365)+(month(createdAt)*30)+day(createdAt))) FROM User";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }*/
    @Override
    public int getUserAverageTimeOfUsingLibrary() {
        List results = this.sessionFactory.getCurrentSession().createQuery("from User").list();
        if ((results == null) || (results.size() == 0)) {
            return 0;
        }
        int daysCount = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate userCreatedAt;
        User user;
        for (Object elem : results) {
            user = (User) elem;
            userCreatedAt = Instant.ofEpochMilli(user.getCreatedAt().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            daysCount += (Period.between(userCreatedAt, currentDate).getDays());
        }
        return daysCount / results.size();
    }

    /**
     * Finds user average age by book ID.
     *
     * @param bookId book ID.
     * @return number of years.
     */
    /*@Override
    public int getUserAverageAgeByBookId(int bookId) {
        String hql = "select (avg(((year(current_date)*365)+(month(current_date)*30)+day(current_date))" +
            "-((year(U.birthdayDate)*365)+(month(U.birthdayDate)*30)+day(U.birthdayDate))))/365 " +
            "from  Order As O left join User AS U On U.id = O.reader.id and O.book.id=:bookId";

        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();

        return ((Double) results.get(0)).intValue();
    }*/
    @Override
    public int getUserAverageAgeByBookId(int bookId) {
        String hql = "select U from Order As O left join User AS U On U.id = O.reader.id and O.book.id=:bookId " +
            "where U.birthdayDate is not null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();
        if ((results == null) || (results.size() == 0)) {
            return 0;
        }
        int yearsCount = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate userBirthdayDate;
        User user;
        for (Object elem : results) {
            user = (User) elem;
            userBirthdayDate = Instant.ofEpochMilli(user.getBirthdayDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            yearsCount += (Period.between(userBirthdayDate, currentDate).getYears());
        }
        return yearsCount / results.size();
    }

    /**
     * Finds user average age by author.
     *
     * @param author author.
     * @return number of years.
     */
    /*@Override
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
    }*/
    @Override
    public int getUserAverageAgeByAuthor(Author author) {
        String hql = "select u from Author a left join a.books b " +
            "inner join b.orders o left join o.reader u where a.id=:authorId " +
            "and u.birthdayDate is not null";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        List results = query.setParameter("authorId", author.getId()).list();
        if ((results == null) || (results.size() == 0)) {
            return 0;
        }
        int yearsCount = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate userBirthdayDate;
        User user;
        for (Object elem : results) {
            user = (User) elem;
            userBirthdayDate = Instant.ofEpochMilli(user.getBirthdayDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            yearsCount += (Period.between(userBirthdayDate, currentDate).getYears());
        }
        return yearsCount / results.size();
    }
}
