package com.softserve.academy.service;

import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;

import java.util.List;

public interface CopyService {
    List<Copy> getAllCopiesByBook(Book book) throws IllegalArgumentException;
}
