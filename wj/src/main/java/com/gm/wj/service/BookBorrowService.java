package com.gm.wj.service;

import com.gm.wj.dao.BookBorrowDAO;
import com.gm.wj.entity.BookBorrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class BookBorrowService {

    @Autowired
    BookBorrowDAO bookBorrowDAO;

    public void add(BookBorrow bookborrow){

        Date startDate=new Date(System.currentTimeMillis());
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE,14);
        Date endDate=new Date(calendar.getTimeInMillis());

        bookborrow.setStartdate(startDate);
        bookborrow.setEnddate(endDate);
        bookBorrowDAO.saveAndFlush(bookborrow);
    }

    public List<BookBorrow> list(String username){
        return(bookBorrowDAO.findAllByUsername(username));
    }

}
