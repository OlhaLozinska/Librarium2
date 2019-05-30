package com.softserve.academy.dao;

import com.mysql.cj.Query;
import com.softserve.academy.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public Book getBookById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getAllBooksWithOrdersCount() {
        String line = "select count(o.book), b from Book b left join b.orders o group by b.id";
        Iterator iter = this.sessionFactory.getCurrentSession().createQuery(line).list().iterator();
        List<Book> books = new ArrayList<>();
        while (iter.hasNext()) {
            Object[] tuple = (Object[])iter.next();
            Book book = (Book) tuple[1];
            book.setOrdersQuantity(((Long)tuple[0]).intValue());
            books.add(book);
        }
        return books;
    }

    @Override
    public int getCountBooksPublishingInPeriodOfIndependence() {
        String hql = "select distinct book.id FROM Copy WHERE publicationYear >1991";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }
}
