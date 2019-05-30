package com.softserve.academy.dao;

import com.softserve.academy.entity.Copy;

import java.util.List;

public interface CopyDao {
    List<Copy> getAllCopiesWithOrdersCountByBookId(Integer bookId);
}
