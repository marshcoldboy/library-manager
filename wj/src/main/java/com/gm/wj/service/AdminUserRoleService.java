package com.gm.wj.service;

import com.gm.wj.dao.AdminUserRoleDAO;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.AdminUserRole;
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
@Transactional
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    /*根据用户id返回记录*/
    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

    /*保存新增的记录*/
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        adminUserRoleDAO.saveAll(urs);
    }
}
