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
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
    List<Book> findAllByTitleLikeOrAuthorLikeOrPressLikeOrIsbnLike(String k1,String k2,String k3,String l4);
    Book findByTitle(String title);
    void deleteByBid(Integer bid);
}
