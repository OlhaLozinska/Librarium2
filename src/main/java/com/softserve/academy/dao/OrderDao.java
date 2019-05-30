package com.softserve.academy.dao;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.User;

import java.util.Date;

public interface OrderDao {
    Integer getOrdersCountByBookId(Integer bookId);

    Integer getMaxOrdersCount();

    boolean orderCopy(User creator, User reader, Book book, Copy copy, Date deadlineDate);
}
