package com.softserve.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Integer getOrdersCountByBookId(Integer bookId) {
        String line = "select count(book) from Order where book.id = :book_id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("book_id", bookId);
        Long count = (Long) query.list().get(0);
        return count.intValue();
    }

    @Override
    public Integer getMaxOrdersCount() {
        String line = "select count(book) from Order group by book";
        List ordersCount = this.sessionFactory.getCurrentSession().createQuery(line).list();
        return ((Long) Collections.max(ordersCount)).intValue();
    }
    @Override
    public int getQuantityOfOrdersInAllPeriod() {
        String hql = "select id FROM Order";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }

}
