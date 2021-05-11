package com.example.demo.pojo.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -2054359538140713354L;

    @TableId("id")
    private Long id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    private Date createTime;
    private Date updateTime;
}

