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
 * Entity class, represents library book's author.
 *
 * @author Olha Lozinska
 * @version 2.0
 * @since 23.05.2019
 */
@Entity
@Table(name = "authors")
public class Author {
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

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @ManyToMany(targetEntity = Book.class, mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;

    /**
     * Default no-args constructor
     */
    public Author() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Checks two Author objects for equality.
     *
     * @param o author
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

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(author.createdAt) : author.createdAt != null) {
            return false;
        }
        if (creator != null ? !creator.equals(author.creator) : author.creator != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) {
            return false;
        }
        return books != null ? books.equals(author.books) : author.books == null;
    }

    /**
     * Calculates hashCode for Author object.
     *
     * @return author's hashCode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }

    /**
     * Forms string representation of Author object.
     *
     * @return string representation of Author object.
     */
    @Override
    public String toString() {
        return "Author{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", creator=" + creator +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", books=" + books +
            '}';
    }
}
