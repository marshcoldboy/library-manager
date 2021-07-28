package com.gm.wj.dao;

import com.gm.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan
 * @date 2019/4
 */
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);/*根据用户名查询对应记录*/
    void deleteByUsername(String username);/*根据用户名删除对应记录*/
    User getByUsernameAndPassword(String username,String password);/*根据用户名和密码查询对应记录*/
}
