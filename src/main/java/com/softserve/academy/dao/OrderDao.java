package com.softserve.academy.dao;

public interface OrderDao {
    int getQuantityOfOrdersInAllPeriod();

    Integer getOrdersCountByBookId(Integer bookId);

    Integer getMaxOrdersCount();
}
