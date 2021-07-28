/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /*角色名*/
    private String name;

    /*中文角色名*/
    @Column(name = "name_zh")
    private String nameZh;

    /*角色权限可用状态*/
    private boolean enabled;


    /*角色拥有许可证列表*/
    @Transient
    private List<AdminPermission> perms;

    /*角色拥有权限菜单列表*/
    @Transient
    private List<AdminMenu> menus;
}
