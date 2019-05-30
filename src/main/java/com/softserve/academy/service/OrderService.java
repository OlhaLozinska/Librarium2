package com.softserve.academy.service;

import com.softserve.academy.entity.User;

public interface OrderService {
    boolean orderCopy(String copyId, String readerId, String bookId, User creator)
        throws IllegalArgumentException;
}
