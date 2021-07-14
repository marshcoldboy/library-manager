package com.gm.wj.dao;

import com.gm.wj.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarCodeDAO extends JpaRepository<AdminUserRole,Integer> {

}
