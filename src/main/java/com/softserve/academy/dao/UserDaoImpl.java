package com.softserve.academy.dao;

import com.softserve.academy.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUsername(String userName) {
        String line = "from User where userName = :user_name";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("user_name", userName);
        List users = query.list();

        if (users.isEmpty()) {
            return null;
        } else {
            return (User) users.get(0);
        }
    }
}
