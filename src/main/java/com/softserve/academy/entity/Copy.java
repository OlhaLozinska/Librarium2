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
import java.util.List;

/**
 *
 * Entity class, represents library book's copy.
 *
 * @author Olha Lozinska
 * @version 2.0
 * @since 23.05.2019
 *
 */
@Entity
@Table(name = "copies")
public class Copy {
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

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Transient
    private Integer ordersQuantity;

    @OneToMany(mappedBy = "copy", fetch = FetchType.LAZY)
    private List<Order> orders;

    /**
     * Default no-args constructor
     */
    public Copy() {
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getOrdersQuantity() {
        return ordersQuantity;
    }

    public void setOrdersQuantity(Integer ordersQuantity) {
        this.ordersQuantity = ordersQuantity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Checks two Copy objects for equality.
     *
     * @param o copy
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

        Copy copy = (Copy) o;

        if (id != null ? !id.equals(copy.id) : copy.id != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(copy.createdAt) : copy.createdAt != null) {
            return false;
        }
        if (creator != null ? !creator.equals(copy.creator) : copy.creator != null) {
            return false;
        }
        if (publicationYear != null ? !publicationYear.equals(copy.publicationYear) : copy.publicationYear != null) {
            return false;
        }
        if (publishingHouse != null ? !publishingHouse.equals(copy.publishingHouse) : copy.publishingHouse != null) {
            return false;
        }
        if (available != null ? !available.equals(copy.available) : copy.available != null) {
            return false;
        }
        if (book != null ? !book.equals(copy.book) : copy.book != null) {
            return false;
        }
        if (ordersQuantity != null ? !ordersQuantity.equals(copy.ordersQuantity) : copy.ordersQuantity != null) {
            return false;
        }
        return orders != null ? orders.equals(copy.orders) : copy.orders == null;
    }

    /**
     * Calculates hashCode for Copy object.
     *
     * @return copy's hashCode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (publicationYear != null ? publicationYear.hashCode() : 0);
        result = 31 * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (ordersQuantity != null ? ordersQuantity.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    /**
     * Forms string representation of Copy object.
     *
     * @return string representation of Copy object.
     */
    @Override
    public String toString() {
        return "Copy{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", creator=" + creator +
            ", publicationYear=" + publicationYear +
            ", publishingHouse='" + publishingHouse + '\'' +
            ", available=" + available +
            ", book=" + book +
            ", ordersQuantity=" + ordersQuantity +
            ", orders=" + orders +
            '}';
    }
}
