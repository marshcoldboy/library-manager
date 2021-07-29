package com.gm.wj.service;

import com.gm.wj.dao.AdminRolePermissionDAO;
import com.gm.wj.entity.AdminPermission;
import com.gm.wj.entity.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    /*根据rid查询所有对应记录*/
    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }

    /*保存修改后的记录*/
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }
}
