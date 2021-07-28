package com.gm.wj.dao;

import com.gm.wj.entity.Book;
import com.gm.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);/*根据书籍种类查询所有对应记录*/
    List<Book> findAllByTitleLikeOrAuthorLikeOrPressLikeOrIsbnLike(String k1,String k2,String k3,String l4);/*对关键字进行模糊搜索*/
    Book findByTitle(String title);/*根据书名查询对应记录*/
    void deleteByBid(Integer bid);/*根据bid删除对应记录*/
}
