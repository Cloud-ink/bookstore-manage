package com.example.demo.pojo.font.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCart {
    private int cart_id;// 购物车id
    private int user_id;
    private int product_id;// 商品id
    private int cart_num;//商品数量
    private boolean cart_checked;// 是否勾选
    private int cart_price;
    private String product_name;// 商品名称
    private String product_image;// 商品图片
    private double product_price; //商品价格
    private int product_stock;// 商品限购数量
}
