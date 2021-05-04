package com.example.demo.service.impl;

import com.example.demo.mapper.ShoppingCartMapper;
import com.example.demo.pojo.font.shoppingcart.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> getShoppingCart(int user_id) {

        return shoppingCartMapper.getShoppingCart(user_id);
    }

    @Override
    public int deleteShoppingCart(int user_id, int product_id) {
        return shoppingCartMapper.deleteShoppingCart(user_id, product_id);
    }

    @Override
    public int addShoppingCart(int user_id, int product_id) {
        return shoppingCartMapper.addShoppingCart(user_id, product_id);
    }

    @Override
    public int updateShoppingCart(int user_id, int product_id, int cart_num) {
        return shoppingCartMapper.updateShoppingCart(user_id, product_id, cart_num);
    }


}
