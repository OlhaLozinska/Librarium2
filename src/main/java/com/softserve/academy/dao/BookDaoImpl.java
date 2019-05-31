/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

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

/**
 * Class which provide default implementation of CRUD operations.
 *
 * @author Olha Lozinska
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 23.05.2019
 */
@Repository
public class BookDaoImpl implements BookDao {
    /**
     * SessionFactory objcet
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Finds book by book's ID.
     *
     * @param id book's ID
     * @return book.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Book getBookById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(Book.class, id);
    }

    /**
     * Finds all books with orders count.
     *
     * @return list of matching books.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getAllBooksWithOrdersCount() {
        String line = "select count(o.book), b from Book b left join b.orders o group by b.id";
        Iterator iter = this.sessionFactory.getCurrentSession().createQuery(line).list().iterator();
        List<Book> books = new ArrayList<>();
        while (iter.hasNext()) {
            Object[] tuple = (Object[]) iter.next();
            Book book = (Book) tuple[1];
            book.setOrdersQuantity(((Long) tuple[0]).intValue());
            books.add(book);
        }
        return books;
    }

    /**
     * Finds ordered list of books in a certain period of dates.
     *
     * @param startDate date of start period.
     * @param endDate   date of end period.
     * @param sortAsc   sort by ascending.
     * @return list of matching books.
     */
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
            Object[] tuple = (Object[]) iter.next();
            Book book = (Book) tuple[1];
            book.setOrdersQuantity(((Long) tuple[0]).intValue());
            books.add(book);
        }
        return books;
    }

    /**
     * Finds count of books publishing in period of Independence.
     *
     * @return number of books.
     */
    @Override
    public int getCountBooksPublishingInPeriodOfIndependence() {
        String hql = "select distinct book.id FROM Copy WHERE publicationYear >1991";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return results.size();
    }

    /**
     * Finds average time of reading by book's ID.
     *
     * @param bookId book's ID
     * @return number of days.
     */
    @Override
    public int getAverageTimeOfReadingByBookId(int bookId) {
        String hql = "select avg(((year(returnDate)*365)+(month(returnDate)*12)+day(returnDate))" +
            "-((year(takeDate)*365)+(month(takeDate)*12)+day(takeDate))) from Order where id=:bookId";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();
        return ((Double) results.get(0)).intValue();
    }
}
