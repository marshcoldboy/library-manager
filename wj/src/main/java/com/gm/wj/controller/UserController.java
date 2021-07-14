package com.gm.wj.controller;

import com.gm.wj.dto.UserDTO;
import com.gm.wj.entity.*;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * User controller.
 *
 * @author Evan
 * @date 2019/11
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/api/admin/user")
    public Result listUsers() {
        List<UserDTO> userDTOS=userService.list();
        return ResultFactory.buildSuccessResult(userDTOS);
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @PostMapping("/api/admin/user/delete")
    public Result userDelete(@RequestBody @Valid User user){
        System.out.println(user);
        userService.deleteByUsername(user.getUsername());
        if(userService.findByUsername(user.getUsername())==null)
            return ResultFactory.buildSuccessResult("成功删除用户");
        else
            return ResultFactory.buildFailResult("删除出错");
    }

    @PostMapping("/api/user-information")
    public Result userInformation(@RequestBody @Valid User user) throws Exception {
        return ResultFactory.buildSuccessResult(userService.findByUsername(user.getUsername()));
    }

    @PostMapping("/api/user-information/alterUser")
    public Result userInformationAlter(@RequestBody @Valid User alterUser){
        userService.editUser(alterUser);
        return ResultFactory.buildSuccessResult(null);
    }
}
