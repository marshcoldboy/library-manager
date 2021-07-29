package com.gm.wj.service;

import com.gm.wj.dao.BookReturnDAO;
import com.gm.wj.entity.Book;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */

@Service
public class BookReturnService {

    @Autowired
    BookReturnDAO bookReturnDAO;
    @Autowired
    BookBorrowService bookBorrowService;
    @Autowired
    FineService fineService;
    @Autowired
    BookService bookService;

    /*保存归还记录*/
    public void save(BookReturn bookReturn){
        bookReturnDAO.saveAndFlush(bookReturn);
    }

    /*管理员同意归还请求*/
    public String adminConsent(int borrowId){
        BookBorrow bookBorrow=bookBorrowService.findByBorrowid(borrowId);

        Book book=bookService.findByTitle(bookBorrow.getTitle());
        book.setAmount(book.getAmount()+1);
        bookService.saveAnfFlush(book);

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

    /*管理员拒接归还请求*/
    public String adminDeny(int borrowId){
        BookBorrow bookBorrow=bookBorrowService.findByBorrowid(borrowId);
        BookReturn bookReturn=bookReturnDAO.findByBookborrow(bookBorrow);
        bookBorrow.setStatus("归还被拒绝，请找管理员核对");
        bookReturn.setDeny(true);
        bookReturnDAO.saveAndFlush(bookReturn);

        return "归还被拒绝，请找管理员核对";
    }

    /*列出所有归还请求记录*/
    public List<BookReturn> list(){
        List<BookReturn> bookReturnList=bookReturnDAO.findAll();
        List<BookReturn> result=new ArrayList<>();
        for(BookReturn i:bookReturnList){
            if(!i.getDeny())
                result.add(i);
        }
        return result;
    }

    /*根据借阅记录查询归还请求记录*/
    public BookReturn findByBookBorrow(BookBorrow bookBorrow){
        return bookReturnDAO.findByBookborrow(bookBorrow);
    }
}
