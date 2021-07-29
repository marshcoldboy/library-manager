package com.gm.wj.dao;

import com.gm.wj.entity.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(int rid);/*根据角色主键查询所有记录*/
    List<AdminRoleMenu> findAllByRid(List<Integer> rids);/*根据角色主键列表查询对应拥有对应rid的所有记录*/
    void deleteAllByRid(int rid);/*根据角色主键删除所有对应记录*/
}
