package com.gm.wj.service;

import com.gm.wj.dao.FineDAO;
import com.gm.wj.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class FineService {

    @Autowired
    FineDAO fineDAO;


}
