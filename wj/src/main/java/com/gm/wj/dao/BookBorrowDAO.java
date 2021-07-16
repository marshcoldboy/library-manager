package com.gm.wj.dao;


import com.gm.wj.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookBorrowDAO extends JpaRepository<BookBorrow,Integer> {

    List<BookBorrow> findAllByUsername(String username);
    BookBorrow findByBorrowid(int id);
}
