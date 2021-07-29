package com.gm.wj.service;

import com.gm.wj.dao.UserDAO;
import com.gm.wj.dto.UserDTO;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/4
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    /*返回所有用户列表*/
    public List<UserDTO> list() {
        List<User> users = userDAO.findAll();

        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    /*根据当前用户返回权限角色列表*/
    public List<AdminRole> roleList(String username){
        return adminRoleService.listRolesByUser(username);
    }

    /*判断用户是否存在*/
    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null != user;
    }

    /*根据用户名查询用户*/
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    /*根据用户名和密码查询用户*/
    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    /*注册用户*/
    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userDAO.save(user);

        return 1;
    }

    /*更新用户禁用状态*/
    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        userDAO.save(userInDB);
    }

    /*重设用户密码*/
    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDAO.save(userInDB);
    }

    /*编辑用户个人信息*/
    public void editUser(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userDAO.saveAndFlush(userInDB);
        //adminUserRoleService.saveRoleChanges(userInDB.getUid(), user.getRoles());
    }

    /*根据主键删除用户*/
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    /*根据用户名删除用户*/
    public void deleteByUsername(String username){userDAO.deleteByUsername(username);}
}
