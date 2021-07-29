package com.gm.wj.service;

import com.gm.wj.dao.CategoryDAO;
import com.gm.wj.entity.Category;
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
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    /*返回所有记录根据主键的降序排列*/
    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    /*根据主键返回对应记录*/
    public Category get(int id) {
        Category c= categoryDAO.findById(id).orElse(null);
        return c;
    }
}
