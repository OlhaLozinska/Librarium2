package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getOrderedListOfBooksInPeriod(Date startDate, Date endDate, boolean sortAsc) {
        String line = "select count(o.book), b from Order o left join o.book b " +
            "where o.takeDate between :start_date and :end_date " +
            "group by o.book.id order by count(o.book) ";
        if (sortAsc) {
            line += "asc";
        } else {
            line += "desc";
        }
        Query query = this.sessionFactory.getCurrentSession().createQuery(line);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);

        Iterator iter = query.list().iterator();
        List<Book> books = new ArrayList<>();
        while (iter.hasNext()) {
            Object[] tuple = (Object[])iter.next();
            Book book = (Book) tuple[1];
            book.setOrdersQuantity(((Long)tuple[0]).intValue());
            books.add(book);
        }
        return books;
    }
}
