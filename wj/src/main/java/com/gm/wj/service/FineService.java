package com.gm.wj.service;

import com.gm.wj.dao.BookBorrowDAO;
import com.gm.wj.dao.FineDAO;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class FineService {

    @Autowired
    FineDAO fineDAO;

    @Autowired
    BookBorrowService bookBorrowService;

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

    public void save(Fine fine){
        fineDAO.saveAndFlush(fine);
    }

    public void refresh(){
        List<Fine> fineList=fineDAO.findAll();
        for(Fine i:fineList)
        {
            BookBorrow temple=i.getBookborrow();
            int days=(int)((temple.getReturndate().getTime()-temple.getEnddate().getTime())/(1000*24*60*60));
            i.setDays(days);
            if(days<=0)
                i.setFine(0.00);
            else
                i.setFine(0.10*days);
        }
    }
}
