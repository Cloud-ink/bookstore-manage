package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.pojo.font.Carousel;
import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.pojo.product.vo.ProductQueryVo;
import com.example.demo.pojo.product.vo.ProductVo;
import com.example.demo.util.VoPage;
import com.example.demo.pojo.product.Product;
import com.example.demo.service.ProductService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @ApiOperation("多表分页查询")
    @Override
    public IPage<ProductVo> selectPage(int page, int limit, ProductQueryVo productQueryVo) {
        //组装查询条件
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        //多表联查一定要加前缀
        queryWrapper.orderByDesc("p.product_createTime");

        long id = productQueryVo.getId();
        Integer categoryId = productQueryVo.getCategoryId();
        String productName = productQueryVo.getProductName();
        String productSubtitle = productQueryVo.getProductSubtitle();
        String productStatus = productQueryVo.getProductStatus();

        if (!StringUtils.isEmpty(categoryId)) {
            queryWrapper.like("p.category_id", categoryId);
        }

        if (!StringUtils.isEmpty(productName)) {
            queryWrapper.like("p.product_name", productName);
        }

        if (!StringUtils.isEmpty(productSubtitle)) {
            queryWrapper.like("p.product_subtitle", productSubtitle);
        }

        if (!StringUtils.isEmpty(productStatus)) {
            queryWrapper.like("p.product_status", productStatus);
        }

        //组装分页
        Page<ProductVo> pageParam = new Page<>(page, limit);

        //执行查询,只要有page类的参数，自动解析page，limit并加到sql中去
        //只需要在mapper层传入组装好的分页组件即可，其他的sql分页条件由mp自动完成
        List<ProductVo> records = baseMapper.selectPageByProductQueryVo(pageParam, queryWrapper);

        System.out.println(records.size());
        return pageParam.setRecords(records);
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public boolean save(ProductVo userVo) {
        return false;
    }

    @Override
    public List<Map<String, Object>> selectNameByKey(String key) {
        return null;
    }
}
