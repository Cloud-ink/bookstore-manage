package com.example.demo.pojo.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("用户查询条件")
@Data
public class ProductVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private long id;
    private String productName;
    private String productImage;
    private int categoryId;
    private String productSubtitle;
    private String productDetail;
    private double productPrice;
    private String productStatus;
    private double productSellingPrice;
    private int productStock;
    private String productSalePoint;
    private Timestamp productCreateTime;
    private Timestamp productUpdateTime;
}