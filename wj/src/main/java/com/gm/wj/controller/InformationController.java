package com.gm.wj.controller;

import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.Fine;
import com.gm.wj.entity.User;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.BookBorrowService;
import com.gm.wj.service.BookReturnService;
import com.gm.wj.service.FineService;
import com.gm.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */

@RestController
public class InformationController {
    @Autowired
    BookBorrowService bookBorrowService;
    @Autowired
    UserService userService;
    @Autowired
    FineService fineService;
    @Autowired
    BookReturnService bookReturnService;

    /*返回用户个人信息*/
    @PostMapping("/api/userCenter/user_information")
    public Result userInformation(@RequestBody @Valid User user) throws Exception {
        return ResultFactory.buildSuccessResult(userService.findByUsername(user.getUsername()));
    }

    /*返回当前用户借阅信息*/
    @PostMapping("/api/userCenter/borrow_information")
    public Result userBorrowInformation(@RequestBody @Valid User user){
        String username=user.getUsername();
        return ResultFactory.buildSuccessResult(bookBorrowService.nowList(username));
    }

    /*返回当前用户历史借阅信息*/
    @PostMapping("/api/userCenter/borrow_history")
    public Result userBorrowHistory(@RequestBody @Valid User user){
        String username=user.getUsername();
        return ResultFactory.buildSuccessResult(bookBorrowService.historyList(username));
    }

    /*根据前端发送信息修改用户信息*/
    @PostMapping("/api/userCenter/user_information/alterUser")
    public Result userInformationAlter(@RequestBody @Valid User alterUser){
        userService.editUser(alterUser);
        return ResultFactory.buildSuccessResult(null);
    }

    /*用户发送归还借阅书籍请求*/
    @PostMapping("/api/userCenter/bookReturn")
    public Result bookReturn(@RequestBody @Valid BookBorrow bookBorrow){
        if(bookBorrowService.returnBook(bookBorrow.getBorrowid())!=null)
            return ResultFactory.buildSuccessResult(null);
        else
            return ResultFactory.buildFailResult("归还出错");
    }

    /*用户发送续借请求*/
    @PostMapping("/api/userCenter/bookRenew")
    public Result bookRenew(@RequestBody @Valid BookBorrow bookBorrow){
        if(bookBorrowService.renewBook(bookBorrow.getBorrowid()))
            return ResultFactory.buildSuccessResult(null);
        else
            return ResultFactory.buildFailResult("您已续借过，无法继续续借");
    }

    /*用户获取个人罚款信息*/
    @PostMapping("/api/userCenter/fine_information")
    public Result fineInformation(@RequestBody @Valid User user){
        return ResultFactory.buildSuccessResult(fineService.fineInformation(user.getUsername()));
    }

    /*用户根据日期查询罚款记录*/
    @PostMapping("/api/userCenter/fineAccordingDate")
    public Result fineAccordingDate(@RequestBody @Valid BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(fineService.fineAccordingDate(bookBorrow));
    }

    /*返回当前所有借阅信息*/
    @GetMapping("/api/admin/borrow_information")
    public Result adminBorrowInformation(){
        return ResultFactory.buildSuccessResult(bookBorrowService.nowList());
    }

    /*返回所有历史借阅信息*/
    @GetMapping("/api/admin/borrow_history")
    public Result adminBorrowHistory(){
        return ResultFactory.buildSuccessResult(bookBorrowService.historyList());
    }

    /*管理员同意归还请求*/
    @PostMapping("/api/admin/book_return/consent")
    public Result adminConsent(@RequestBody @Valid BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookReturnService.adminConsent(bookBorrow.getBorrowid()));
    }

    /*管理员拒接归还请求*/
    @PostMapping("/api/admin/book_return/deny")
    public Result adminDeny(@RequestBody @Valid BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookReturnService.adminDeny(bookBorrow.getBorrowid()));
    }

    /*管理员获得当前所有请求归还信息*/
    @GetMapping("/api/admin/book_return_information")
    public Result returnInformation(){
        return ResultFactory.buildSuccessResult(bookReturnService.list());
    }
}
