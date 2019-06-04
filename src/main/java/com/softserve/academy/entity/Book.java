/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity class, represents library book.
 *
 * @author Volodymyr Oseredchuk
 * @version 2.0
 * @since 23.05.2019
 */
@Entity
@Table(name = "books")
public class Book {
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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "page_quantity")
    private Integer pageQuantity;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinTable(name = "bookauthor", joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors;

    @Transient
    private String imageUrl;

    @Transient
    private Integer ordersQuantity;

    @Transient
    private Integer rating;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Copy> copies;

    /**
     * Default no-args constructor
     */
    public Book() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageQuantity() {
        return pageQuantity;
    }

    public void setPageQuantity(Integer pageQuantity) {
        this.pageQuantity = pageQuantity;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getOrdersQuantity() {
        return ordersQuantity;
    }

    public void setOrdersQuantity(Integer ordersQuantity) {
        this.ordersQuantity = ordersQuantity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    /**
     * Checks two Book objects for equality.
     *
     * @param o book
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

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(book.createdAt) : book.createdAt != null) {
            return false;
        }
        if (creator != null ? !creator.equals(book.creator) : book.creator != null) {
            return false;
        }
        if (name != null ? !name.equals(book.name) : book.name != null) {
            return false;
        }
        if (description != null ? !description.equals(book.description) : book.description != null) {
            return false;
        }
        if (pageQuantity != null ? !pageQuantity.equals(book.pageQuantity) : book.pageQuantity != null) {
            return false;
        }
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) {
            return false;
        }
        if (imageUrl != null ? !imageUrl.equals(book.imageUrl) : book.imageUrl != null) {
            return false;
        }
        if (ordersQuantity != null ? !ordersQuantity.equals(book.ordersQuantity) : book.ordersQuantity != null) {
            return false;
        }
        if (rating != null ? !rating.equals(book.rating) : book.rating != null) {
            return false;
        }
        if (orders != null ? !orders.equals(book.orders) : book.orders != null) {
            return false;
        }
        return copies != null ? copies.equals(book.copies) : book.copies == null;
    }

    /**
     * Calculates hashCode for Book object.
     *
     * @return book's hashCode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pageQuantity != null ? pageQuantity.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (ordersQuantity != null ? ordersQuantity.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (copies != null ? copies.hashCode() : 0);
        return result;
    }

    /**
     * Forms string representation of Book object.
     *
     * @return string representation of Book object.
     */
    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", creator=" + creator +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", pageQuantity=" + pageQuantity +
            ", authors=" + authors +
            ", imageUrl='" + imageUrl + '\'' +
            ", ordersQuantity=" + ordersQuantity +
            ", rating=" + rating +
            ", orders=" + orders +
            ", copies=" + copies +
            '}';
    }
}
