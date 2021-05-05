package com.example.demo.controller;

import com.example.demo.pojo.font.gategory.Category;
import com.example.demo.pojo.font.gategory.VoCategory;
import com.example.demo.pojo.product.Product;
import com.example.demo.pojo.user.VoEmployee;
import com.example.demo.pojo.utils.VoPage;
import com.example.demo.res.Res;
import com.example.demo.res.ResCategory;
import com.example.demo.res.Result;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductService;
import com.example.demo.utils.Constants;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Result getlist(@RequestParam("page") int pageNo,
                          @RequestParam("limit") int limit,
                          @RequestParam("sort") String idSort){
        Result res = null;
        VoPage page = productService.searchProduct(pageNo, limit, idSort);
        res = new Result(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
        return res;
    }

    //删除商品
    @PostMapping("/delete")
    public Result deleteProduct(@RequestParam("product_id") int product_id){
        Result res = new Result();
        Product pro = new Product();
        productService.deleteByPrimaryKey(product_id);
        res.setData(pro);
        res.setCode(Constants.STATUS_OK);
        res.setMessage(Constants.MESSAGE_OK);
        return res;
    }

    //更新商品状态
    @PostMapping("/changeStatus")
    public Result changeProductStatus(@RequestParam("product_id") int product_id,
                                      @RequestParam("product_status") String status){
        Result res = new Result();
//        Product pro = new Product();
//        pro.setProduct_id(product_id);
//        pro.setProduct_status(status);
//        System.out.println(pro.getProduct_status());
//        productService.changeProductStatus(pro);
//        res.setData(pro);
//        res.setCode(Constants.STATUS_OK);
//        res.setMessage(Constants.MESSAGE_OK);
        return res;
    }

}
