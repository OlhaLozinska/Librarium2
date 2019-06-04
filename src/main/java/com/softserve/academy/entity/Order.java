/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class, represents library orders table.
 *
 * @author Volodymyr Oseredchuk
 * @version 2.0
 * @since 23.05.2019
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    private User reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copy_id", referencedColumnName = "id")
    private Copy copy;

    @Column(name = "take_date")
    @Temporal(TemporalType.DATE)
    private Date takeDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "deadline_date")
    @Temporal(TemporalType.DATE)
    private Date deadlineDate;

    /**
     * Default no-args constructor
     */
    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Checks two Order objects for equality.
     *
     * @param o order
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(order.createdAt) : order.createdAt != null) {
            return false;
        }
        if (creator != null ? !creator.equals(order.creator) : order.creator != null) {
            return false;
        }
        if (reader != null ? !reader.equals(order.reader) : order.reader != null) {
            return false;
        }
        if (book != null ? !book.equals(order.book) : order.book != null) {
            return false;
        }
        if (copy != null ? !copy.equals(order.copy) : order.copy != null) {
            return false;
        }
        if (takeDate != null ? !takeDate.equals(order.takeDate) : order.takeDate != null) {
            return false;
        }
        if (returnDate != null ? !returnDate.equals(order.returnDate) : order.returnDate != null) {
            return false;
        }
        return deadlineDate != null ? deadlineDate.equals(order.deadlineDate) : order.deadlineDate == null;
    }

    /**
     * Calculates hashCode for Order object.
     *
     * @return order's hashCode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (reader != null ? reader.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (copy != null ? copy.hashCode() : 0);
        result = 31 * result + (takeDate != null ? takeDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (deadlineDate != null ? deadlineDate.hashCode() : 0);
        return result;
    }

    /**
     * Forms string representation of Order object.
     *
     * @return string representation of Order object.
     */
    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", creator=" + creator +
            ", reader=" + reader +
            ", book=" + book +
            ", copy=" + copy +
            ", takeDate=" + takeDate +
            ", returnDate=" + returnDate +
            ", deadlineDate=" + deadlineDate +
            '}';
    }
}
