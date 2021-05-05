package com.example.demo.pojo.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("admin")
public class Admin implements Serializable {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    @TableField(value = "admin_name")
    private String adminName;

    @TableField(value = "admin_password")
    private String adminPassword;

    @TableField(value = "admin_avatar")
    private String adminAvatar;

    @TableField(value = "admin_role")
    private String adminRole;
}
