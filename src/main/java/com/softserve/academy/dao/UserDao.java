package com.softserve.academy.dao;

import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Integer id);
    int getUserStatisticAverageAge();
    int getUserAverageTimeOfUsingLibrary();
    User getUserByUsername(String userName);

    int getUserAverageAgeByBookId(int bookId);

    int getUserAverageAgeByAuthor(Author author);


}
