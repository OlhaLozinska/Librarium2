package com.softserve.academy.dao;

import com.mysql.cj.Query;
import com.softserve.academy.entity.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getAllBooks() {
        return this.sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    @Override
    public int getCountBooksPublishingInPeriodOfIndependence() {
        String hql = "select distinct book.id FROM Copy WHERE publicationYear >1991";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }
}
