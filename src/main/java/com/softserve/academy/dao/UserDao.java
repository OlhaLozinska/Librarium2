package com.softserve.academy.dao;

import com.softserve.academy.entity.User;

public interface UserDao {
    User getUserById(Integer id);

    int getUserStatisticAverageAge();

    int getUserAverageTimeOfUsingLibrary();

    int getUserAverageAgeByBookId(int bookId);
}
