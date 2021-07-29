package com.gm.wj.dao;

import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface BookReturnDAO extends JpaRepository<BookReturn,Integer> {
    BookReturn findByBookborrow(BookBorrow bookBorrow);/*根据bookBorrow实体查询对应归还记录*/
    void deleteByBookborrow(BookBorrow bookBorrow);/*根据bookBorrow实体删除对应归还记录*/
}
