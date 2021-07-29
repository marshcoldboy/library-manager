package com.gm.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.wj.entity.Category;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
