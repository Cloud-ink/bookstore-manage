package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.font.Carousel;
import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.pojo.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends IService<Product> {
    List<Product> selectProduct();

    int insert(Product record);

    int insertSelective(Product record);

    int deleteByPrimaryKey(int product_id);

    Product selectByPrimaryKey(Long id);
    //更新商品状态
    int changeProductStatus(Product product);

    int updateByPrimaryKey(Product record);

    int updateByPrimaryKeySelective(Product record);
    //查询商品分类列表
    List<Category> getCategoryList();
    //更新商品状态
    int changeCategoryStatus(Category category);


    Product selectProductByProductId(int product_id);
    //获取轮播图列表
    List<Carousel> getCarouselList();
    //根据分类名获取商品
    List<Product> getProductListByCategoryName(String category_name);
    //根据分类id获取商品
    List<Product> getProductListByCategoryId(int category_id);
    //根据搜索获取商品
    List<Product> getProductListBySearch(String search);
}
