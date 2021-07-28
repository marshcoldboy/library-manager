package com.gm.wj.dao;

import com.gm.wj.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);/*根据主键查询记录*/
}
