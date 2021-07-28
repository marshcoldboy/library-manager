/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_permission")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    许可证名
    private String name;

//    许可证描述
    private String desc_;

//    许可证检查路径许可证检查路径
    private String url;
}
