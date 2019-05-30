package com.softserve.academy.service;

import com.softserve.academy.dao.CopyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyDao copyDao;
}
