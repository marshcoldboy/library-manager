package com.gm.wj.service;

import com.gm.wj.dao.BookBorrowDAO;
import com.gm.wj.dao.FineDAO;
import com.gm.wj.entity.Book;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class FineService {

    @Autowired
    FineDAO fineDAO;

    @Autowired
    BookBorrowService bookBorrowService;

    /*返回当前用户的所有罚款记录*/
    public List<Fine> fineInformation(String username){
        List<Fine> userFine=fineDAO.findAllByUsername(username);
        for(Fine i:userFine)
        {
            if(i.getFine()==0.0)
                i.setStatus("按期归还");
            else
                i.setStatus("需缴纳罚款"+i.getFine()+"元");
        }
        return userFine;
    }

    /*保存罚款记录*/
    public void save(Fine fine){
        fineDAO.saveAndFlush(fine);
    }

    public void refresh(){
        List<Fine> fineList=fineDAO.findAll();
        for(Fine i:fineList)
        {
            BookBorrow temple=i.getBookborrow();
            int days=(int)((temple.getReturndate().getTime()-temple.getEnddate().getTime())/(1000*24*60*60));
            i.setDays(Math.max(days,0));
            i.setFine(i.getDays()*0.1);
        }
    }

    /*刷新所有的罚款记录*/
    public void renew(){
        List<BookBorrow> borrowList=bookBorrowService.historyList();
        List<Fine> fineList=new ArrayList<Fine>();
        fineDAO.deleteAll();

        for(BookBorrow i:borrowList)
        {
            Fine temple=new Fine(i);
            fineList.add(temple);
        }
        fineDAO.saveAll(fineList);
    }

    /*根据日期查询罚款记录*/
    public List<Fine> fineAccordingDate(BookBorrow bookBorrow){
        Date startDate=bookBorrow.getStartdate();
        Date returnDate=bookBorrow.getReturndate();
        List<Fine> bookFineList=fineDAO.findAllByUsername(bookBorrow.getUsername());
        List<Fine> result=new ArrayList<>();
        for(Fine i:bookFineList){
            if (i.getBookborrow().getStartdate().getTime() >= startDate.getTime() && i.getBookborrow().getReturndate().getTime() <= returnDate.getTime()) {
                if(i.getFine()==0.0)
                    i.setStatus("按期归还");
                else
                    i.setStatus("需缴纳罚款"+i.getFine()+"元");
                result.add(i);
            }
        }
        return result;
    }
}
