package com.gm.wj.dao;

import com.gm.wj.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineDAO extends JpaRepository<Fine,Integer> {

    List<Fine> findAllByUsername(String username);
}
