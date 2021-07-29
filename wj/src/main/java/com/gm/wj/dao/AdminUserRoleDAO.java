package com.gm.wj.dao;

import com.gm.wj.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);/*根据uid查询所有对应记录*/
    void deleteAllByUid(int uid);/*根据uid删除所有对应记录*/
}
