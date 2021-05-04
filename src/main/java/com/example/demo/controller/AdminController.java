package com.example.demo.controller;

import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.user.UserInfo;
import com.example.demo.pojo.utils.Token;
import com.example.demo.res.Result;
import com.example.demo.service.AdminService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    //登录
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Result res = new Result();
        Admin s = this.adminService.verify(admin);
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();

        if(adminName.equals("admin")){
            if (adminPassword.equals("1234")){
                String token = JwtUtil.sign(admin.getAdminName(),"-1");
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(new Token(token));
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage("密码错误");
            }
        }else{
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage("未找到用户");
        }
        return res;
    }

    //登录用户信息
    @GetMapping("/info")
    public Result getinfo(@RequestParam String token){
        Result res = new Result();
        String tokenValue = JwtUtil.verity(token);
        if (tokenValue!=null && tokenValue.startsWith(JwtUtil.TOKEN_SUCCESS)) {
            // 如果欧克，返回用户信息，字符串为 success: username
            //将字符串中的success替换成空，这样就得到用户名
            String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS, "");
            UserInfo info = new UserInfo();
            info.setAvatar("https://www.baidu.com/link?url=5_iO22PAfqKj3qebUhfnNNJTTJbQu3JWKsjAd-xUKIwRrs8oxUNnUC3x1O_shpTFQNCJDh8DFVf6wYwZyH9fdK&wd=&eqid=fd24c5d200000c0200000004603ca2d0");
            info.setIntroduction("测试用户");
            info.setName("zhang1");
            List<String> roles = Arrays.asList("admin");
            info.setRoles(roles);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(info);
        }
        return res;
    }

    //登出
    @PostMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token){
        Result res = new Result();
        String tokenValue = JwtUtil.verity(token);
        String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS,"");
        res.setMessage("logout");
        res.setCode(Constants.STATUS_OK);
        res.setData("asd");
        return res;
    }
}
