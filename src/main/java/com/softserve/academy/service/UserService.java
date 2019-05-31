package com.softserve.academy.service;

import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id) throws IllegalArgumentException;

    int getUserStatisticAverageAge();

    int getUserAverageTimeOfUsingLibrary();

    int getUserAverageAgeByBookId(int bookId);

    int getUserAverageAgeByAuthor(Author author);

    List<Integer> getUsersAverageAgesForAuthors(List<Author> authors);

}
