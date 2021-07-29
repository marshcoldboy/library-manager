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
 * @author Team BraveNiuNiu
 * @date 2021/7
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

    /*所有记录降序返回*/
    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "bid");
        return bookDAO.findAll(sort);
    }

    /*增加或修改记录*/
    public void addOrUpdate(Book book) {
        bookDAO.saveAndFlush(book);
    }

    /*根据bid删除记录*/
    public void deleteByBid(int bid) {
        bookDAO.deleteByBid(bid);
    }

    /*返回同一种类的图书列表*/
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    /*根据关键词来搜索*/
    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLikeOrPressLikeOrIsbnLike('%' + keywords + '%', '%' + keywords + '%', '%' + keywords + '%', '%' + keywords + '%');
    }

    /*根据书名搜索记录*/
    public Book findByTitle(String title){
        return bookDAO.findByTitle(title);
    }

    /*判断当前书籍数量是否足够借阅*/
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

    /*保存记录*/
    public void saveAnfFlush(Book book){
        bookDAO.saveAndFlush(book);
    }
}
