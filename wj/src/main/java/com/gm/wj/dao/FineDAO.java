package com.gm.wj.dao;

import com.gm.wj.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */

public interface FineDAO extends JpaRepository<Fine,Integer> {
    List<Fine> findAllByUsername(String username);/*根据用户名查询该用户的所有罚款记录*/
}
