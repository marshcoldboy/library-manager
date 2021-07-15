package com.gm.wj.controller;

import com.gm.wj.entity.Book;
import com.gm.wj.entity.BookBorrow;
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

    @PostMapping("/api/user-information")
    public Result userInformation(@RequestBody @Valid User user) throws Exception {
        return ResultFactory.buildSuccessResult(userService.findByUsername(user.getUsername()));
    }

    @PostMapping("/api/borrow_information")
    public Result borrowInformation(@RequestBody @Valid User user){
        String username=user.getUsername();
        return ResultFactory.buildSuccessResult(bookBorrowService.newList(username));
    }

    @PostMapping("/api/borrow_history")
    public Result borrowHistory(@RequestBody @Valid User user){
        String username=user.getUsername();
        return ResultFactory.buildSuccessResult(bookBorrowService.historyList(username));
    }

    @PostMapping("/api/user-information/alterUser")
    public Result userInformationAlter(@RequestBody @Valid User alterUser){
        userService.editUser(alterUser);
        return ResultFactory.buildSuccessResult(null);
    }
}
