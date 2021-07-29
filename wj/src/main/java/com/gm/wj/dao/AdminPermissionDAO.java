package com.gm.wj.dao;

import com.gm.wj.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);/*根据主键查询记录*/
}
