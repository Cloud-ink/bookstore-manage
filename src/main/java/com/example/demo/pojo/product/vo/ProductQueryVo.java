package com.example.demo.pojo.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("商品查询条件")
@Data
public class ProductQueryVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private long id;
    private Integer categoryId;
    private String productName;
    private String productSubtitle;
    private String productStatus;

}
