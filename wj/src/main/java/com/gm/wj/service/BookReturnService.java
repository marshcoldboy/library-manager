package com.gm.wj.service;

import com.gm.wj.dao.BookReturnDAO;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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

        BookReturn bookReturn=bookReturnDAO.findByBookborrow(bookBorrow);
        bookReturnDAO.delete(bookReturn);

        bookBorrow.setReturndate(new Date(System.currentTimeMillis()));
        long time=bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime();
        bookBorrow.setStatus(time<=0?"按时归还":"逾期归还");
        bookBorrowService.save(bookBorrow);

        Fine fine=new Fine(bookBorrow);
        fineService.save(fine);

        return  "归还成功";
    }

    public String adminDeny(int borrowId){
        BookBorrow bookBorrow=bookBorrowService.findByBorrowid(borrowId);
        BookReturn bookReturn=bookReturnDAO.findByBookborrow(bookBorrow);
        bookBorrow.setStatus("归还被拒绝，请找管理员核对");
        bookReturn.setDeny(true);
        bookReturnDAO.saveAndFlush(bookReturn);

        return "归还被拒绝，请找管理员核对";
    }

    public List<BookReturn> list(){
        List<BookReturn> bookReturnList=bookReturnDAO.findAll();
        List<BookReturn> result=new ArrayList<>();
        for(BookReturn i:bookReturnList){
            if(!i.getDeny())
                result.add(i);
        }
        return result;
    }

    public BookReturn findByBookBorrow(BookBorrow bookBorrow){
        return bookReturnDAO.findByBookborrow(bookBorrow);
    }

}
