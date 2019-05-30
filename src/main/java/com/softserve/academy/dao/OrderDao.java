package com.softserve.academy.dao;

public interface OrderDao {
    Integer getOrdersCountByBookId(Integer bookId);

    Integer getMaxOrdersCount();
}
