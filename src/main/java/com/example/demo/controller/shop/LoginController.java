package com.example.demo.controller.shop;

import com.example.demo.res.Result;
import com.example.demo.service.LoginService;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.utils.Constants.STATUS_FAIL;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    AdminService adminService;

    @PostMapping("/api/users/register")
    public Result register(@RequestParam("userName") String username,
                           @RequestParam("pass") String pass){
        Result res = new Result();
        if(loginService.register(username,pass)) {
            res.setMessage("注册成功");
            res.setCode(20000);
        }else{
            res.setMessage("注册失败");
            res.setCode(STATUS_FAIL);
        }
        return res;
    }

    @PostMapping("/api/users/findUserName")
    public Result findUserName(@RequestParam("userName") String username){
        Result res = new Result();
        if(loginService.findUserName(username)) {
            res.setMessage("可以注册");
            res.setCode(20000);
        }else{
            res.setMessage("已注册");
            res.setCode(STATUS_FAIL);
        }
        return res;
    }
}
