package com.example.demo.controller;

import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.res.Result;
import com.example.demo.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    //获取分类列表
//    @GetMapping("/category/list")
//    public Result getCategoryList(@RequestParam("page") int pageNo,
//                                  @RequestParam("limit") int limit){
//        Result res = null;
//        List<Category> list = new ArrayList<>();
//        list.addAll(productService.getCategoryList());
//        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, list);
//        return res;
//    }
//    //更新商品状态
//    @PostMapping("/category/changeStatus")
//    public Result changeStatus(@RequestParam("category_id") int category_id,
//                               @RequestParam("category_status") String category_status){
//        Result res = new Result();
//        Category pro = new Category();
//        pro.setCategory_id(category_id);
//        pro.setCategory_status(category_status);
//        System.out.println(pro.getCategory_status());
//        productService.changeCategoryStatus(pro);
//        res.setData(pro);
//        res.setCode(Constants.STATUS_OK);
//        res.setMessage(Constants.MESSAGE_OK);
//        return res;
//    }
}
