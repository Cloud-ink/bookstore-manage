package com.example.demo.pojo.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminVo {

    private int id;

    private String adminName;

    private String adminPassword;

    private String adminAvatar;

    private String adminPhone;

    private boolean enabled;

}
