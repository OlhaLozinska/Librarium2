package com.softserve.academy.service;

import com.softserve.academy.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id) throws IllegalArgumentException;

    int getUserStatisticAverageAge();

    int getUserAverageTimeOfUsingLibrary();

    User getRegisteredUser(String username, String password) throws IllegalArgumentException;
}
