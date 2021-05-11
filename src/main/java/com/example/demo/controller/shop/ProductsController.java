package com.example.demo.controller.shop;
import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.pojo.font.gategory.VoCategory;
import com.example.demo.pojo.product.Product;
import com.example.demo.util.VoPage;
import com.example.demo.service.ProductService;
import com.example.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ProductsController {
    @Autowired
    ProductService productService;
    //获取商品分类列表
//    @PostMapping("/category/list")
//    public VoCategory getCategory(){
//        VoCategory res = new VoCategory();
//        List<Category> category = new ArrayList<>();
//        category.addAll(productService.getCategoryList());
//        res.setCode(20000);
//        res.setCategory(category);
//        return res;
//    }
    //获取全部商品
//    @PostMapping("/api/product/getAllProduct")
//    public Result getAllProduct( @RequestParam("currentPage") int currentPage,
//                                 @RequestParam("pageSize") int limit,
//                                 @RequestParam(value = "sort",required = false,defaultValue = "0") String idSort,
//                                 @RequestParam(value = "categoryID",required = false,defaultValue = "0") int categoryId,
//                                 @RequestParam(value = "title",required = false,defaultValue = "0")int title){
//        Result res = null;
//        VoPage page = productService.searchProduct(1, 3);
//        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
//        return res;
//    }
//    //获取商品详细信息
//    @PostMapping("/api/product/getDetails")
//    public Result getProductDetails( @RequestParam("productID") int productID){
//        Result res = null;
//        System.out.println(productID);
//        Product product = new Product();
//        product = productService.getProductByProductId(productID);
//        //VoPage page = productService.searchProduct(1, 3);
//        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, product);
//        return res;
//    }
//    //根据分类名获取商品信息
//    @PostMapping("/api/product/getPromoProduct")
//    public Result getProductByName(@RequestParam("categoryName") String category_name){
//        Result res = new Result();
//        List<Product> list = new ArrayList<>();
//        list.addAll(productService.getProductListByCategoryName(category_name));
//        res.setCode(20000);
//        res.setMessage("获取成功");
//        res.setData(list);
//        return res;
//    }
//    //根据分类获取商品
//    @PostMapping("/api/product/getProductByCategory")
//    public Result getProductByCategory(@RequestParam("categoryID") int category_id,
//                                       @RequestParam("currentPage") int currentPage,
//                                       @RequestParam("pageSize") int pageSize){
//        Result res = null;
//        VoPage page = productService.getProductListByCategoryId(category_id);
//        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
//        return res;
//    }
//    //根据搜索获取商品
//    @PostMapping("/api/product/getProductBySearch")
//    public Result getProductBySearch(@RequestParam("search") String search,
//                                     @RequestParam("currentPage") int currentPage,
//                                     @RequestParam("pageSize") int pageSize){
//        Result res = null;
//        VoPage page = productService.getProductListBySearch(search);
//        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
//        return res;
//    }
}
