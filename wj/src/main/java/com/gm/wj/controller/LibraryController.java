package com.gm.wj.controller;

import com.gm.wj.entity.*;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.BookBorrowService;
import com.gm.wj.service.BookService;
import com.gm.wj.service.CategoryService;
import com.gm.wj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @Autowired
    BookBorrowService bookBorrowService;

    @Autowired
    CategoryService categoryService;
    private @Valid Object bookBorrow;

    /*增加借阅信息*/
    @PostMapping("/api/BookBorrow")
    public Result subscribe(@RequestBody @Valid BookBorrow bookBorrow){
        if(!bookService.borrowJudge(bookBorrow.getTitle()))
            return ResultFactory.buildFailResult("无剩余可借藏本");
        else {
            bookBorrowService.add(bookBorrow);
            return ResultFactory.buildSuccessResult("修改成功");
        }
    }

    /*返回所有书籍信息*/
    @GetMapping("/api/books")
    public Result listBooks() {
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    /*管理员增加或修改图书*/
    @PostMapping("/api/admin/content/books")
    public Result addOrUpdateBooks(@RequestBody @Valid Book book) {
        Category category=categoryService.get(book.getCategory().getCid());
        book.setCategory(category);
        bookService.addOrUpdate(book);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    /*管理员删除图书*/
    @PostMapping("/api/admin/content/books/delete")
    public Result deleteBook(@RequestBody @Valid Book book) {
        bookService.deleteByBid(book.getBid());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    /*管理员删除所有选中图书*/
    @PostMapping("/api/admin/content/books/deleteSelectedBooks")
    public Result deleteAllBook(@RequestBody @Valid Book books){
        int[] bids=books.getBids();
        for (int i:bids) {
            bookService.deleteByBid(i);
        }
        return ResultFactory.buildSuccessResult("删除成功");
    }

    /*根据关键词查询图书*/
    @GetMapping("/api/search")
    public Result searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(bookService.list());
        } else {
            return ResultFactory.buildSuccessResult(bookService.Search(keywords));
        }
    }

    /*根据日期和用户名查询历史借阅记录*/
    @PostMapping("/api/admin/borrow_history_all_accordingDateAndUsername")
    public Result adminSearchAccordingDateAndUsername(@RequestBody @Valid BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookBorrowService.findByDateAndUsername(bookBorrow));
    }

    /*根据日期查询历史借阅记录*/
    @PostMapping("/api/admin/borrow_history_all_accordingDate")
    public Result adminSearchAccordingDate(BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookBorrowService.findByDate(bookBorrow));
    }

    /*根据用户名查询历史借阅记录*/
    @PostMapping("/api/admin/borrow_history_all_accordingUsername")
    public Result adminSearchAccordingUsername(@RequestBody @Valid User user){
        String username=user.getUsername();
        if(!user.getUsername().equals(""))
            return ResultFactory.buildSuccessResult(bookBorrowService.historyList(username));
        else
            return ResultFactory.buildSuccessResult(bookBorrowService.historyList());
    }

    /*根据类别返回所有书籍*/
    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
        } else {
            return ResultFactory.buildSuccessResult(bookService.list());
        }
    }

    /*上传书籍封面*/
    @PostMapping("/api/admin/content/books/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
