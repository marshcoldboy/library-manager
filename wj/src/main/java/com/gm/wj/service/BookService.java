package com.gm.wj.service;

import com.gm.wj.dao.BookDAO;
import com.gm.wj.entity.Book;
import com.gm.wj.entity.Category;
import com.gm.wj.redis.RedisService;
import com.gm.wj.util.CastUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
@Service
@Transactional
public class BookService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisService redisService;

    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "bid");
        return bookDAO.findAll(sort);
    }

    public void addOrUpdate(Book book) {
        bookDAO.saveAndFlush(book);
    }

    public void deleteByBid(int bid) {
        bookDAO.deleteByBid(bid);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLikeOrPressLikeOrIsbnLike('%' + keywords + '%', '%' + keywords + '%', '%' + keywords + '%', '%' + keywords + '%');
    }

    public Book findByTitle(String title){
        return bookDAO.findByTitle(title);
    }

    public Boolean borrowJudge(String title){
        Book book=this.findByTitle(title);
        int amount=book.getAmount();
        if(amount==0)
            return false;
        else{
            book.setAmount(--amount);
            bookDAO.saveAndFlush(book);
            return true;
        }
    }

    public void saveAnfFlush(Book book){
        bookDAO.saveAndFlush(book);
    }
}
