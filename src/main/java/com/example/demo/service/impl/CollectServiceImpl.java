package com.example.demo.service.impl;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.pojo.font.Collect;
import com.example.demo.pojo.product.Product;
import com.example.demo.service.CollectService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Product> getCollectByUserId(int user_id) {
        return orderMapper.getCollectByUserId(user_id);
    }

    @Override
    public int addCollectByUserId(int user_id, int product_id) {
        return orderMapper.addCollectByUserId(user_id,product_id);
    }

    @Override
    public int deleteCollectByUserId(int user_id, int product_id) {
        return orderMapper.deleteCollectByUserId(user_id,product_id);
    }
}
