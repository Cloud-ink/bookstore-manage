package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.font.Carousel;
import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.pojo.product.Product;
import com.example.demo.pojo.product.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductVo> selectPageByProductQueryVo(
            Page<ProductVo> pageParam,
            //mp自动组装querywrapper，@Param(Constants.WRAPPER)和xml中${ew.customSqlSegment}对应
            @Param(Constants.WRAPPER)QueryWrapper<Product> queryWrapper
            );
}
