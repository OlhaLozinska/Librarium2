package com.softserve.academy.service;

import com.softserve.academy.dao.BookDao;
import com.softserve.academy.dao.CopyDao;
import com.softserve.academy.dao.OrderDao;
import com.softserve.academy.dao.UserDao;
import com.softserve.academy.entity.Book;
import com.softserve.academy.entity.Copy;
import com.softserve.academy.entity.User;
import com.softserve.academy.entity.UserType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Calendar;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CopyDao copyDao;

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public boolean orderCopy(String copyId, String readerId, String bookId, User creator)
        throws IllegalArgumentException {
        if ((copyId == null) || (readerId == null) || (bookId == null) ||
            (copyId.isEmpty()) || (readerId.isEmpty()) || (bookId.isEmpty())) {
            throw new IllegalArgumentException("Id is not valid");
        }

        if ((creator == null) || (creator.getUserType() == UserType.USER)) {
            throw new IllegalArgumentException("Creator is not valid or permission denied");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date deadlineDate = new Date(calendar.getTimeInMillis());

        User reader;
        Book book;
        Copy copy;
        try {
            int copy_id = Integer.parseInt(copyId);
            int reader_id = Integer.parseInt(readerId);
            int book_id = Integer.parseInt(bookId);
            if (copy_id <= 0 || reader_id <= 0 || book_id <= 0) {
                throw new IllegalArgumentException("Id is not valid");
            }

            reader = userDao.getUserById(reader_id);
            book = bookDao.getBookById(book_id);
            copy = copyDao.getCopyById(copy_id);
            if (reader == null || book == null || copy == null) {
                throw new IllegalArgumentException("Object doesn't exist");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("Id is not valid");
        }


        return orderDao.orderCopy(creator, reader, book, copy, deadlineDate);
    }
}
