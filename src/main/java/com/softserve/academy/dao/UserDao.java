package com.softserve.academy.dao;

import com.softserve.academy.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Integer id);

    User getUserByUsername(String userName);
}
