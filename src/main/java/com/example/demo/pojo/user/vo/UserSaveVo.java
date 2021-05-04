package com.example.demo.pojo.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户查询条件")
@Data
public class UserSaveVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户Id")
    private long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户号码")
    private int userPhone;

    @ApiModelProperty("用户性别")
    private int userGender;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "user_password")
    private String userPassword;

    @ApiModelProperty(value = "用户邮箱")
    @TableField(value = "user_email")
    private String userEmail;

    @ApiModelProperty(value = "用户地址")
    @TableField(value = "user_address")
    private String userAddress;
}