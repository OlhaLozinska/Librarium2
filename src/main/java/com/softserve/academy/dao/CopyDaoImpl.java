package com.softserve.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CopyDaoImpl implements CopyDao {

    @Autowired
    private SessionFactory sessionFactory;
}
