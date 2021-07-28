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
        bookborrow.setStatus("借阅中");
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
//                bookBorrow.setStatus("借阅中");
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
//                long time=bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime();
//                if (time <= 0) {
//                    bookBorrow.setStatus("按时归还");
//                } else {
//                    bookBorrow.setStatus("逾期归还");
//                }
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
//                long time=bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime();
//                bookBorrow.setStatus(time<=0?"按时归还":"逾期归还");
                result.add(bookBorrow);
            }
        }
        return result;
    }

    public BookBorrow returnBook(int borrow_id){
        BookBorrow bookBorrow=bookBorrowDAO.findByBorrowid(borrow_id);
        bookBorrow.setStatus("申请归还中，等待管理员审核");
        BookReturn bookReturn = bookReturnService.findByBookBorrow(bookBorrow);
        if(bookReturn==null)
            bookReturn=new BookReturn(bookBorrow,new Date(System.currentTimeMillis()));
        else
            bookReturn.setDeny(false);
        bookBorrowDAO.saveAndFlush(bookBorrow);
        bookReturnService.save(bookReturn);

        return bookBorrow;
    }

    public Boolean renewBook(int borrow_id){
        BookBorrow bookBorrow=bookBorrowDAO.findByBorrowid(borrow_id);
        Date startDate=bookBorrow.getStartdate();
        Date endDate=bookBorrow.getEnddate();

        int days=(int)((endDate.getTime()-startDate.getTime())/(60*24*24*1000));
        if(days>=14)
            return false;

        calendar.setTime(endDate);
        calendar.add(Calendar.DATE,7);
        endDate=new Date(calendar.getTimeInMillis());
        bookBorrow.setEnddate(endDate);
        return true;
    }

    public BookBorrow findByBorrowid(int borrow_id){
        return bookBorrowDAO.findByBorrowid(borrow_id);
    }

    public List<BookBorrow> findByDateAndUsername(BookBorrow bookBorrow){
        Date startDate=bookBorrow.getStartdate();
        Date returnDate=bookBorrow.getReturndate();
        List<BookBorrow> bookBorrowList;
        List<BookBorrow> result=new ArrayList<>();
        if(!bookBorrow.getUsername().equals(""))
            bookBorrowList = this.historyList(bookBorrow.getUsername());
        else
            bookBorrowList=this.historyList();
        if(startDate==null&&returnDate==null)
            return bookBorrowList;
        for(BookBorrow i:bookBorrowList){
            if(i.getReturndate()!=null) {
                if (i.getStartdate().getTime() >= startDate.getTime() && i.getReturndate().getTime() <= returnDate.getTime())
                    result.add(i);
            }
        }
        return result;
    }

    public List<BookBorrow> findByDate(BookBorrow bookBorrow){
        Date startDate=bookBorrow.getStartdate();
        Date returnDate=bookBorrow.getReturndate();
        List<BookBorrow> bookBorrowList=bookBorrowDAO.findAll();
        List<BookBorrow> result=new ArrayList<>();
        for(BookBorrow i:bookBorrowList){
            if (i.getStartdate().getTime() >= startDate.getTime() && i.getReturndate().getTime() <= returnDate.getTime())
                result.add(i);
        }
        return result;
    }

    public void save(BookBorrow bookBorrow){
        bookBorrowDAO.saveAndFlush(bookBorrow);
    }
}
