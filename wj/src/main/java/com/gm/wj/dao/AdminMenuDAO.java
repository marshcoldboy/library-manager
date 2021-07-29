package com.gm.wj.dao;

import com.gm.wj.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
     AdminMenu findById(int id);/*根据主键查询记录*/
     List<AdminMenu> findAllByParentId(int parentId);/*根据父菜单主键查询所有菜单*/
}

