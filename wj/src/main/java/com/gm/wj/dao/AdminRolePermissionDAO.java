package com.gm.wj.dao;

import com.gm.wj.entity.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission, Integer> {
    List<AdminRolePermission> findAllByRid(int rid);/*根据rid查询所有所有对应记录*/
    List<AdminRolePermission> findAllByRid(List<Integer> rids);/*根据所有rid查询对应的记录*/
    void deleteAllByRid(int rid);/*根据rid删除对应记录*/
}
