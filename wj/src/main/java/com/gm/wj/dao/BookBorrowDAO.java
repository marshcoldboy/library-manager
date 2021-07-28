package com.gm.wj.dao;


import com.gm.wj.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookBorrowDAO extends JpaRepository<BookBorrow,Integer> {
    List<BookBorrow> findAllByUsername(String username);/*根据用户名查询所有对应记录*/
    BookBorrow findByBorrowid(int id);/*根据borrowid查询所有对应记录*/
}
