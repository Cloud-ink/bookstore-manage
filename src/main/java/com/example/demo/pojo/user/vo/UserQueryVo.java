package com.example.demo.pojo.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户查询条件")
@Data
public class UserQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户Id")
    private long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户号码")
    private int userPhone;

    @ApiModelProperty("用户性别")
    private String userGender;

}
