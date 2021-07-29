package com.gm.wj.dao;

import com.gm.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);/*根据用户名查询对应记录*/
    void deleteByUsername(String username);/*根据用户名删除对应记录*/
    User getByUsernameAndPassword(String username,String password);/*根据用户名和密码查询对应记录*/
}
