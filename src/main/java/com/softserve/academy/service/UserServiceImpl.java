package com.softserve.academy.service;

import com.softserve.academy.dao.UserDao;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int getUserAverageTimeOfUsingLibrary() {
        return userDao.getUserAverageTimeOfUsingLibrary();
    }
}
