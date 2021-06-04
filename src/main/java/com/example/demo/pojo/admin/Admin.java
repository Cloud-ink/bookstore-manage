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
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("admin")
public class Admin implements Serializable {

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    @TableField(value = "admin_name")
    private String adminName;

    @TableField(value = "admin_password")
    private String adminPassword;

    @TableField(value = "admin_avatar")
    private String adminAvatar;

    @TableField(value = "admin_phone")
    private String adminPhone;

    @TableField(value = "enabled")
    private boolean enabled;

    @TableField(value = "type")
    private String type;

    @TableField(value = "admin_role")
    private String role;


    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(value = "update_time")
    private Timestamp updateTime;
}
