package com.gm.wj.dao;

import com.gm.wj.entity.BookBorrow;
import com.gm.wj.entity.BookReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReturnDAO extends JpaRepository<BookReturn,Integer> {

    BookReturn findByBookborrow(BookBorrow bookBorrow);
    void deleteByBookborrow(BookBorrow bookBorrow);
}
