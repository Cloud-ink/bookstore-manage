package com.example.demo.pojo.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "商品对象",description = "商品")
@TableName("product")
public class Product implements Serializable {

    @ApiModelProperty(value = "用户Id")
    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    @ApiModelProperty(value = "商品名")
    @TableField(value = "product_name")
    private String productName;

    @ApiModelProperty(value = "商品图片地址")
    @TableField(value = "product_image")
    private String productImage;

    @ApiModelProperty(value = "商品价格")
    @TableField(value = "product_price")
    private Long productPrice;

    @ApiModelProperty(value = "商品售价")
    @TableField(value = "product_salePrices")
    private Long productSalePrices;

    @ApiModelProperty(value = "商品卖点")
    @TableField(value = "product_sale_point")
    private String productSalePoint;

    @ApiModelProperty(value = "商品状态")
    @TableField(value = "product_status")
    private String productStatus;

    @ApiModelProperty(value = "商品分类")
    @TableField(value = "category_id")
    private Long categoryId;

    @ApiModelProperty(value = "商品")
    @TableField(value = "product_typeName")
    private String productTypeName;

    @ApiModelProperty(value = "商品状态")
    @TableField(value = "product_flag")
    private Boolean productFlag;

    @ApiModelProperty(value = "商品更新")
    @TableField(value = "product_updateTime")
    private Date productUpdateTime;

    @ApiModelProperty(value = "商品时间")
    @TableField(value = "product_createTime")
    private Date productCreateTime;

    @ApiModelProperty(value = "商品创建人")
    @TableField(value = "product_createUser")
    private Long productCreateUser;

    @ApiModelProperty(value = "商品卖价")
    @TableField(value = "product_selling_price")
    private Double productSellingPrice;

    @ApiModelProperty(value = "商品简介")
    @TableField(value = "product_subtitle")
    private String productSubtitle;

    @ApiModelProperty(value = "商品详情")
    @TableField(value = "product_detail")
    private String productDetail;

    @ApiModelProperty(value = "商品库存")
    @TableField(value = "product_stock")
    private int productStock;
}
