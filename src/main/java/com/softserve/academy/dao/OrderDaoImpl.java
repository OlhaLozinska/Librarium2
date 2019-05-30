package com.softserve.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getQuantityOfOrdersInAllPeriod() {
        String hql = "select id FROM Order";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }

}
