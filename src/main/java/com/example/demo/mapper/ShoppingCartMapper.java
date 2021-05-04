package com.example.demo.mapper;

import com.example.demo.pojo.font.shoppingcart.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> getShoppingCart(int user_id);
    //删除购物车
    int deleteShoppingCart(int user_id,int product_id);
    //添加购物车
    int addShoppingCart(int user_id,int product_id);
    //更新购物车
    int updateShoppingCart(int user_id,int product_id,int cart_num);

}
