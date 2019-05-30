package com.softserve.academy.dao;

import com.softserve.academy.entity.Copy;

import java.util.List;

public interface CopyDao {
    Copy getCopyById(Integer id);

    List<Copy> getAllCopiesWithOrdersCountByBookId(Integer bookId);
}
