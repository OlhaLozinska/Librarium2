package com.softserve.academy.dao;

import com.softserve.academy.entity.User;
import org.hibernate.SessionFactory;
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
    public User getUserById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public double getUserStatisticAverageAge() {
        String hql = "select avg(year(current_date)-year(birthdayDate)) FROM User WHERE birthdayDate is not null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return (Double) results.get(0);
    }

}

