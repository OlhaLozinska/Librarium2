package com.softserve.academy.service;

import com.softserve.academy.dao.UserDao;
import com.softserve.academy.entity.Author;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    @Transactional
    public User getUserById(Integer id) {
        if ((id == null) || (id <= 0)) {
            throw new IllegalArgumentException("User ID is not valid");
        }
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public int getUserStatisticAverageAge() {
        return userDao.getUserStatisticAverageAge();
    }


    @Override
    @Transactional
    public int getUserAverageAgeByBookId(int bookId) {
        return userDao.getUserAverageAgeByBookId(bookId);
    }


    @Override
    @Transactional
    public int getUserAverageTimeOfUsingLibrary() {
        return userDao.getUserAverageTimeOfUsingLibrary();
    }

    @Override
    @Transactional
    public List<Integer> getUsersAverageAgesForAuthors(List<Author> authors) throws IllegalArgumentException {
        if (authors == null) {
            throw new IllegalArgumentException("Authors list is null");
        }
        List<Integer> averageAges = new ArrayList<>();
        for (Author author : authors) {
            averageAges.add(userDao.getUserAverageAgeByAuthor(author));
        }
        return averageAges;
    }

    @Override
    @Transactional
    public int getUserAverageAgeByAuthor(Author author) {
        return userDao.getUserAverageAgeByAuthor(author);
    }

}
