package com.gm.wj.controller;

import com.gm.wj.entity.Book;
import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.Fine;
import com.gm.wj.entity.User;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.BookBorrowService;
import com.gm.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InformationController {
    @Autowired
    BookBorrowService bookBorrowService;
    @Autowired
    UserService userService;

    private List<Integer> historyBorrowids;

    @PostMapping("/api/userCenter/user_information")
    public Result userInformation(@RequestBody @Valid User user) throws Exception {
        return ResultFactory.buildSuccessResult(userService.findByUsername(user.getUsername()));
    }

    @PostMapping("/api/userCenter/borrow_information")
    public Result borrowInformation(@RequestBody @Valid User user){
        String username=user.getUsername();
        return ResultFactory.buildSuccessResult(bookBorrowService.newList(username));
    }

    @PostMapping("/api/userCenter/borrow_history")
    public Result borrowHistory(@RequestBody @Valid User user){
        String username=user.getUsername();
        List<BookBorrow> borrowList=bookBorrowService.historyList(username);
        historyBorrowids.clear();
        for(BookBorrow i:borrowList)
            historyBorrowids.add(i.getBorrowid());
        return ResultFactory.buildSuccessResult(borrowList);
    }

    @PostMapping("/api/userCenter/user_information/alterUser")
    public Result userInformationAlter(@RequestBody @Valid User alterUser){
        userService.editUser(alterUser);
        return ResultFactory.buildSuccessResult(null);
    }

    @PostMapping("/api/userCenter/bookReturn")
    public Result bookReturn(@RequestBody @Valid BookBorrow bookBorrow){
        if(bookBorrowService.returnBook(bookBorrow.getBorrowid())!=null)
            return ResultFactory.buildSuccessResult(null);
        else
            return ResultFactory.buildFailResult("归还出错");
    }

    @PostMapping("/api/userCenter/bookRenew")
    public Result bookRenew(@RequestBody @Valid BookBorrow bookBorrow){
        System.out.println(bookBorrowService.renewBook(bookBorrow.getBorrowid()));
        return ResultFactory.buildSuccessResult(null);
    }

    @PostMapping("/api/userCenter/fine_information")
    public Result fineInformation(@RequestBody @Valid String fine){
//        System.out.println(fine);
//        List<Fine> fineList=null;
//        for (int i:historyBorrowids) {
//            Fine temple=new Fine(bookBorrowService.findByBorrowid(i));
//            fineList.add(temple);
//        }

        return ResultFactory.buildSuccessResult(null);
    }

    @PostMapping("/api/userCenter/fineAccordingDate")
    public Result fineAccordingDate(@RequestBody @Valid String fine){
        System.out.println(fine);
        return ResultFactory.buildSuccessResult(null);
    }
}
