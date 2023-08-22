package com.atguigu.spring6.tx.service;

import com.atguigu.spring6.tx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;


    @Override
    public void buyBook(Integer bookId, Integer userId) {
        Integer price=bookDao.getBookPriceByBookId(bookId);

        bookDao.updateStock(bookId);

        bookDao.updateUserBalance(userId,price);
    }
}
