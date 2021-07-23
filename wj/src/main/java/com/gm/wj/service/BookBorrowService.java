package com.gm.wj.service;

import com.gm.wj.dao.BookBorrowDAO;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class BookBorrowService {

    @Autowired
    BookBorrowDAO bookBorrowDAO;
    @Autowired
    BookReturnService bookReturnService;

    Calendar calendar=new GregorianCalendar();

    public void add(BookBorrow bookborrow){
        Date startDate=new Date(System.currentTimeMillis());
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE,14);
        Date endDate=new Date(calendar.getTimeInMillis());
//        Date returnDate=new Date(0,0,1);
        bookborrow.setStartdate(startDate);
        bookborrow.setEnddate(endDate);
//        bookborrow.setReturndate(returnDate);
        bookBorrowDAO.saveAndFlush(bookborrow);
    }

    public List<BookBorrow> list(String username){
        return(bookBorrowDAO.findAllByUsername(username));
    }

    public List<BookBorrow> nowList(String username){
        List<BookBorrow> bookBorrowList=bookBorrowDAO.findAllByUsername(username);
        List<BookBorrow> result=new ArrayList<BookBorrow>();
        Date date=new Date(System.currentTimeMillis());
        for (BookBorrow bookBorrow:bookBorrowList) {
            if(bookBorrow.getReturndate()==null) {
                int days=(int) ((bookBorrow.getEnddate().getTime() - date.getTime()) / (24 * 60 * 60 * 1000));
                bookBorrow.setDays(Math.max(days, 0));
                result.add(bookBorrow);
            }
        }
        return result;
    }

    public List<BookBorrow> nowList(){
        List<BookBorrow> bookBorrowList=bookBorrowDAO.findAll();
        List<BookBorrow> result=new ArrayList<BookBorrow>();
        for (BookBorrow bookBorrow:bookBorrowList) {
            if(bookBorrow.getReturndate()==null) {
                int days=(int) ((bookBorrow.getEnddate().getTime() - bookBorrow.getStartdate().getTime()) / (24 * 60 * 60 * 1000));
                bookBorrow.setStatus(days>14?"是":"否");
                result.add(bookBorrow);
            }
        }
        return result;
    }

    public List<BookBorrow> historyList(String username){
        List<BookBorrow> bookBorrowList=bookBorrowDAO.findAllByUsername(username);
        List<BookBorrow> result=new ArrayList<BookBorrow>();
        for (BookBorrow bookBorrow:bookBorrowList) {
            if(bookBorrow.getReturndate()!=null) {
                long time=bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime();
                if (time <= 0) {
                    bookBorrow.setStatus("按时归还");
                } else {
                    bookBorrow.setStatus("逾期归还");
                }
                result.add(bookBorrow);
            }
        }
        return result;
    }

    public List<BookBorrow> historyList(){
        List<BookBorrow> bookBorrowList=bookBorrowDAO.findAll();
        List<BookBorrow> result=new ArrayList<BookBorrow>();
        for (BookBorrow bookBorrow:bookBorrowList) {
            if(bookBorrow.getReturndate()!=null) {
                long time=bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime();
                bookBorrow.setStatus(time<=0?"按时归还":"逾期归还");
                result.add(bookBorrow);
            }
        }
        return result;
    }

    public BookBorrow returnBook(int borrow_id){
        BookBorrow bookBorrow=bookBorrowDAO.findByBorrowid(borrow_id);

        BookReturn bookReturn=new BookReturn(bookBorrow,new Date(System.currentTimeMillis()));
        bookReturnService.save(bookReturn);

        return bookBorrow;
    }

    public BookBorrow renewBook(int borrow_id){
        BookBorrow bookBorrow=bookBorrowDAO.findByBorrowid(borrow_id);
        Date endDate=bookBorrow.getEnddate();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE,7);
        endDate=new Date(calendar.getTimeInMillis());
        bookBorrow.setEnddate(endDate);
        return bookBorrowDAO.saveAndFlush(bookBorrow);
    }

    public BookBorrow findByBorrowid(int borrow_id){
        return findByBorrowid(borrow_id);
    }



    public void save(BookBorrow bookBorrow){
        bookBorrowDAO.saveAndFlush(bookBorrow);
    }
}
