/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "admin_menu")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //打开菜单的路径
    private String path;

    //菜单名字
    private String name;

    //菜单中文名
    private String nameZh;

    //菜单所用图标
    private String iconCls;

    //对应前端组件
    private String component;

    //父菜单
    private int parentId;

    //子菜单（不在数据库中记录）
    @Transient
    private List<AdminMenu> children;
}
