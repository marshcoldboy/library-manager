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
 * Library controller.
 *
 * @author Evan
 * @date 2019/4
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

    @PostMapping("/api/BookBorrow")
    public Result subscribe(@RequestBody @Valid BookBorrow bookBorrow){
        bookBorrowService.add(bookBorrow);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @GetMapping("/api/books")
    public Result listBooks() {
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    @PostMapping("/api/admin/content/books")
    public Result addOrUpdateBooks(@RequestBody @Valid Book book) {
        Category category=categoryService.get(book.getCategory().getCid());
        book.setCategory(category);
        bookService.addOrUpdate(book);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @PostMapping("/api/admin/content/books/delete")
    public Result deleteBook(@RequestBody @Valid Book book) {
        bookService.deleteByBid(book.getBid());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @PostMapping("/api/admin/content/books/deleteSelectedBooks")
    public Result deleteAllBook(@RequestBody @Valid Book books){
        int[] bids=books.getBids();
        for (int i:bids) {
            bookService.deleteByBid(i);
        }
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("/api/search")
    public Result searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(bookService.list());
        } else {
            return ResultFactory.buildSuccessResult(bookService.Search(keywords));
        }
    }

    @PostMapping("/api/admin/borrow_history_all_accordingDateAndUsername")
    public Result adminSearchAccordingDateAndUsername(@RequestBody @Valid BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookBorrowService.findByDateAndUsername(bookBorrow));
    }

    @PostMapping("/api/admin/borrow_history_all_accordingDate")
    public Result adminSearchAccordingDate(BookBorrow bookBorrow){
        return ResultFactory.buildSuccessResult(bookBorrowService.findByDate(bookBorrow));
    }

    @PostMapping("/api/admin/borrow_history_all_accordingUsername")
    public Result adminSearchAccordingUsername(@RequestBody @Valid User user){
        String username=user.getUsername();
        if(!user.getUsername().equals(""))
            return ResultFactory.buildSuccessResult(bookBorrowService.historyList(username));
        else
            return ResultFactory.buildSuccessResult(bookBorrowService.historyList());
    }

    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
        } else {
            return ResultFactory.buildSuccessResult(bookService.list());
        }
    }

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
