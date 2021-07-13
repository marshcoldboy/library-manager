package com.gm.wj.service;

import com.gm.wj.dao.BookBorrowDAO;
import com.gm.wj.entity.BookBorrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookBorrowService {

    @Autowired
    BookBorrowDAO bookBorrowDAO;

    public void add(BookBorrow bookborrow){
        bookBorrowDAO.saveAndFlush(bookborrow);
    }

}
