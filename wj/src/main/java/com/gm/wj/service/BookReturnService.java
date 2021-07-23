package com.gm.wj.service;

import com.gm.wj.dao.BookReturnDAO;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BookReturnService {

    @Autowired
    BookReturnDAO bookReturnDAO;
    @Autowired
    BookBorrowService bookBorrowService;
    @Autowired
    FineService fineService;

    public void save(BookReturn bookReturn){
        bookReturnDAO.saveAndFlush(bookReturn);
    }

    public String adminConsent(int borrowId){
        BookBorrow bookBorrow=bookBorrowService.findByBorrowid(borrowId);

        bookReturnDAO.deleteByBookborrow(bookBorrow);

        bookBorrow.setReturndate(new Date(System.currentTimeMillis()));
        bookBorrowService.save(bookBorrow);

        Fine fine=new Fine(bookBorrow);
        fineService.save(fine);

        return  "归还成功";
    }

    public String adminDeny(int borrowId){
        BookBorrow bookBorrow=bookBorrowService.findByBorrowid(borrowId);
        BookReturn bookReturn=bookReturnDAO.findByBookborrow(bookBorrow);
        bookReturn.setDeny(true);
        bookReturnDAO.saveAndFlush(bookReturn);

        return "归还被拒绝，请找管理员核对";
    }

    public List<BookReturn> list(){
        return bookReturnDAO.findAll();
    }
}
