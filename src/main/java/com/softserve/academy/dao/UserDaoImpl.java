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
import com.softserve.academy.entity.UserType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Session session; // = this.sessionFactory.getSessionFactory().openSession();
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.get(User.class, id);
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
    @Override
    public int getUserAverageTimeOfUsingLibrary() {
        String hql = "select avg(((year(current_date)*365)+(month(current_date)*30)+day(current_date))" +
                "-((year(createdAt)*365)+(month(createdAt)*30)+day(createdAt))) FROM User";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    /**
     * Finds user average age by book ID.
     *
     * @param bookId book ID.
     * @return number of years.
     */
    @Override
    public int getUserAverageAgeByBookId(int bookId) {
        String hql = "select (avg(((year(current_date)*365)+(month(current_date)*30)+day(current_date))" +
                "-((year(U.birthdayDate)*365)+(month(U.birthdayDate)*30)+day(U.birthdayDate))))/365 " +
                "from  Order As O left join User AS U On U.id = O.reader.id and O.book.id=:bookId";

        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();

        return ((Double) results.get(0)).intValue();
    }

    /**
     * Finds user average age by author.
     *
     * @param author author.
     * @return number of years.
     */
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

    @Override
    public List<User> getAllDebtors() {

        String hql = "select u " +
                "from Order o left join o.reader u\n" +
                "where current_date > o.deadlineDate and o.returnDate is null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results;
    }

    @Override
    public boolean userSave(User user) {
        //Session session; //= //this.sessionFactory.getCurrentSession();

        //session = this.sessionFactory.openSession();
        Session session = this.sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
    //    session.save(e);


        Date nowDate = new Date();
        User user1 = new User();

        user1.setCreatedAt(nowDate);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
       // user1.setUserName("adrewq");
      //  user1.setPassword("1234");
        user1.setPhone(user.getPhone());
        user1.setAddress(user.getAddress());
        user1.setCreator(user.getCreator());
        user1.setUserType(UserType.USER);

        session.save(user1);
        System.out.println(user1.getId());
        session.getTransaction().commit();
        session.clear();



        return true;

    }

    @Override
    public int getDaysOfUsingLibraryByUser(User user) {
        int value = 654;
      /*  String hql = "select datediff(convert(NOW(), date), CONVERT(created_at, date)) as daysOfUsing  \n" +
                "from users\n" +
                "where id = ?";

        List<Integer> results = this.sessionFactory.getCurrentSession().createNativeQuery(hql).setParameter(1, user.getId()).list();
        value=results.get(0).intValue();*/
        return value;
    }
}
