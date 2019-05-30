package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
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
    public boolean orderCopy(User creator, User reader, Book book, Copy copy, Date deadlineDate) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        String line = "insert into Order (creator, reader, book, copy, deadlineDate) " +
            "values (:creator, :reader, :book, :copy, :deadlineDate)";
        Query query  = session.createQuery(line);
        query.setParameter("creator", creator);
        query.setParameter("reader", reader);
        query.setParameter("book", book);
        query.setParameter("copy", copy);
        query.setParameter("deadlineDate", deadlineDate);
        int rowsAffected = query.executeUpdate();

        if (rowsAffected == 0) {
            tx.rollback();
            session.close();
            return false;
        }

        String newLine = "update Copy set available = :available where id = :copyId";
        Query newQuery = session.createQuery(newLine);
        newQuery.setParameter("available", true);
        newQuery.setParameter("copyId", copy.getId());
        rowsAffected = newQuery.executeUpdate();

        if (rowsAffected == 0) {
            tx.rollback();
            session.close();
            return false;
        } else {
            tx.commit();
            session.close();
            return true;
        }
    }
}
