package com.softserve.academy.service;

import com.softserve.academy.entity.User;

public interface UserService {
    User getUserById(Integer id) throws IllegalArgumentException;

    double getUserStatisticAverageAge();

    double getUserAverageTimeOfUsingLibrary();
}
