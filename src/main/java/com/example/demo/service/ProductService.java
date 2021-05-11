package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.product.Product;
import com.example.demo.pojo.product.vo.ProductQueryVo;
import com.example.demo.pojo.product.vo.ProductVo;
import com.example.demo.pojo.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

public interface ProductService extends IService<Product> {
    @ApiOperation("获取列表")
    List<Product> getProductList();

    @ApiOperation("分页")
    IPage<ProductVo> selectPage(int page, int limit, ProductQueryVo productQueryVo);

    @ApiOperation("删除")
    int deleteById(int id);

    @ApiOperation("添加")
    boolean save(ProductVo userVo);

    List<Map<String, Object>> selectNameByKey(String key);
}
