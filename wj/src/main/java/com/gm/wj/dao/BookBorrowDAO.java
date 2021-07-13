package com.gm.wj.dao;


import com.gm.wj.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowDAO extends JpaRepository<BookBorrow,Integer> {
}
