package com.softserve.academy.dao;

import com.softserve.academy.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User getUserById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }
}
