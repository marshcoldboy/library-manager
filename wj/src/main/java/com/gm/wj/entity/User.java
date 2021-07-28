/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int uid;

    /*用户名*/
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /*密码（加密储存在数据库中）*/
    private String password;

    /*盐粒*/
    private String salt;

    /*用户真实姓名*/
    private String name;

    /*用户电话*/
    private String phone;

    /*用户邮箱*/
    @Email(message = "请输入正确的邮箱")
    private String email;

    /*用户封号状态*/
    private boolean enabled;

    /**
     * Transient property for storing role owned by current user.
     */
    /*用户拥有权限角色列表*/
    @Transient
    private List<AdminRole> roles;

}

