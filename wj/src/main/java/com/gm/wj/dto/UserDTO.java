package com.gm.wj.dto;

import com.gm.wj.dto.base.OutputConverter;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int uid;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<AdminRole> roles;

}
