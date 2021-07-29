package com.gm.wj.controller;

import com.gm.wj.dto.UserDTO;
import com.gm.wj.entity.*;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    BookBorrowService bookBorrowService;

    /*返回所有角色信息*/
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        List<UserDTO> userDTOS=userService.list();
        return ResultFactory.buildSuccessResult(userDTOS);
    }

    /*更新用户状态*/
    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    /*重置用户密码*/
    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    /*修改用户信息*/
    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    /*管理员删除用户*/
    @PostMapping("/api/admin/user/delete")
    public Result userDelete(@RequestBody @Valid User user){
        //System.out.println(user);
        userService.deleteByUsername(user.getUsername());
        if(userService.findByUsername(user.getUsername())==null)
            return ResultFactory.buildSuccessResult("成功删除用户");
        else
            return ResultFactory.buildFailResult("删除出错");
    }

    /*判断当前登录用户是否是管理员*/
    @PostMapping("/api/isAdmin")
    public Result isAdmin(@RequestBody @Valid User user){
        System.out.println(user);
        List<AdminRole> roleList=userService.roleList(user.getUsername());
        for(AdminRole i:roleList)
        {
            if(i.getId()==1||i.getId()==2)
                return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("不是管理员");
    }
}
