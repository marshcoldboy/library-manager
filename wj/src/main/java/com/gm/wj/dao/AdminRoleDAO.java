package com.gm.wj.dao;

import com.gm.wj.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);/*根据主键查询记录*/
}
