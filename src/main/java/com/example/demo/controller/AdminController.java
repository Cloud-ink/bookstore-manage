package com.example.demo.controller;

import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.JwtInfo;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.res.R;
import com.example.demo.service.AdminService;
import com.example.demo.service.SysRoleService;
import com.example.demo.util.JwtUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@ApiModel("管理员")
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(
            @ApiParam(value = "管理员对象", required = true)
            @RequestBody Admin adminVo) {

            Admin admin = adminService.verity(adminVo);
            boolean result = adminService.findAdmin(adminVo);
            if (result) {
                JwtInfo jwtInfo = new JwtInfo();
                jwtInfo.setId(admin.getId());
                jwtInfo.setAdminName(admin.getAdminName());
                jwtInfo.setAdminAvatar(admin.getAdminAvatar());

                String token = JwtUtil.getToken(jwtInfo);
                return R.ok().data("token", token).message("登录成功");
            } else {
                return R.ok().message("未找到用户");
            }

    }

    @ApiOperation("登录用户信息")
    @GetMapping("/info")
    public R getInfo(HttpServletRequest request) {
        boolean result = JwtUtil.checkToken(request);
        if(result) {
            JwtInfo jwtInfo = JwtUtil.getMemberIdByJwtToken(request);
            Set<SysRole> roles = sysRoleService.
            return R.ok().data("userInfo", jwtInfo);
        }else {
            return R.ok().message("token为空");
        }
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public R logout(@RequestHeader("token") String token) {

        return R.ok();
    }
}
