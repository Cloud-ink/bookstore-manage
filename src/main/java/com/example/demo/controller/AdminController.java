package com.example.demo.controller;

import com.example.demo.config.BookStoreException;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.JwtInfo;
import com.example.demo.pojo.user.UserInfo;
import com.example.demo.pojo.utils.Token;
import com.example.demo.res.R;
import com.example.demo.res.Result;
import com.example.demo.res.ResultCodeEnum;
import com.example.demo.service.AdminService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public R login(
            @ApiParam(value = "管理员对象", required = true)
            @RequestBody Admin adminVo) {

        String adminName = adminVo.getAdminName();
        String adminPassword = adminVo.getAdminPassword();

        if (adminName.isEmpty()) return R.ok().message("用户名为空");
        if (adminPassword.isEmpty()) return R.ok().message("密码为空");

        try {
            出错
            Admin admin = adminService.verity(adminVo);
            if (admin.equals(null)) {
                System.out.println("meimeimei");
                return R.error().message("用户名或密码错误");
            } else {
                JwtInfo jwtInfo = new JwtInfo();
                jwtInfo.setId(admin.getId());
                jwtInfo.setAdminName(admin.getAdminName());
                jwtInfo.setAdminAvatar(admin.getAdminAvatar());
                jwtInfo.setAdminRole(admin.getAdminRole());

                String token = JwtUtil.getToken(jwtInfo);
                return R.ok().data("token", token).message("登录成功");
            }
        } catch (Exception e){
            throw new BookStoreException(ResultCodeEnum.LOGIN_ERROR);
        }

    }

    //登录用户信息
    @GetMapping("/info")
    public R getInfo(HttpServletRequest request) {
        try {
            JwtInfo jwtInfo = JwtUtil.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo", jwtInfo);
        } catch (Exception e) {
            //log.error("解析失败：" + e.getMessage());
            throw new BookStoreException(ResultCodeEnum.LOGIN_ERROR);
        }
    }

    //登出
    @PostMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token) {
        Result res = new Result();
//        String tokenValue = JwtUtil.verity(token);
//        String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS,"");
        res.setMessage("logout");
        res.setCode(Constants.STATUS_OK);
        res.setData("asd");
        return res;
    }
}
