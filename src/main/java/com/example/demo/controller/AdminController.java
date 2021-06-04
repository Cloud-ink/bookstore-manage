package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.AdminVo;
import com.example.demo.pojo.admin.vo.JwtAdminInfo;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.res.R;
import com.example.demo.service.AdminService;
import com.example.demo.service.SysRoleService;
import com.example.demo.util.JwtUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        //查找是否存在该用户
        boolean result = adminService.findAdmin(adminVo);
        if(result == false) return R.ok().message("未找到用户");;

        Admin admin = adminService.verity(adminVo);
        if(adminVo.getAdminPassword().equals(admin.getAdminPassword())){
            JwtAdminInfo jwtAdminInfo = new JwtAdminInfo();
            jwtAdminInfo.setId(admin.getId());
            jwtAdminInfo.setAdminName(admin.getAdminName());
            jwtAdminInfo.setAdminAvatar(admin.getAdminAvatar());
            jwtAdminInfo.setRole();
            jwtAdminInfo.setPermissions(permission);
            jwtAdminInfo.set

            String token = JwtUtil.createToken(jwtAdminInfo);
            return R.ok().data("token", token).message("登录成功");
        }

        List<String> permission = null;

//        if(!CollectionUtils.isEmpty(roleSet)){
//            List<Long> idList = new ArrayList<>();
//            for (SysRole sysRole:roleSet) {
//                idList.add(sysRole.getId());
//            }
//            permission = sysRoleService.selectPermission(idList);
//        }

        if (result) {
        } else {
            return R.ok().message("未找到用户");
        }

    }

    @ApiOperation("登录用户信息")
    @GetMapping("/info")
    public R getInfo(HttpServletRequest request) {
        boolean result = JwtUtil.checkToken(request);
        if (result) {
//            JwtAdminInfo jwtAdminInfo = JwtUtil.getMemberIdByJwtToken(request);
//            return R.ok().data("userInfo", jwtAdminInfo);
            return R.ok().message("token为空");
        } else {
            return R.ok().message("token为空");
        }
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public R logout(@RequestHeader("token") String token) {

        return R.ok();
    }

    @ApiOperation("查寻管理员列表")
    @GetMapping("/list/{page}/{limit}")
    public R queryAdmin(@PathVariable("page") int page,
                       @PathVariable("limit") int limit,
                       @ApiParam("查询条件") JwtAdminInfo jwtAdminInfo) {
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pageModel = adminService.selectPage(pageParam, jwtAdminInfo);
        List<Admin> records = pageModel.getRecords();

        long total = pageModel.getTotal();
        if (total == 0) {
            return R.ok().message("未找到用户");
        }
        return R.ok().data("total", total).data("items", records);
    }

    @ApiOperation("添加管理员")
    @PostMapping("/save")
    public R addUser(@ApiParam(value = "管理员对象", required = true)
                     @RequestBody AdminVo adminVo) {
        adminService.save(adminVo);
        return R.ok().message("保存成功");
    }

    @ApiOperation("获取管理员信息")
    @GetMapping("/get/{id}")
    public R getAdmin(@ApiParam(value = "用户id", required = true)
                     @PathVariable int id) {
        System.out.println(id);
        Admin admin = adminService.getById(id);
        if (admin.equals(null)) {
            return R.ok().message("未查找到用户");
        } else {
            return R.ok().message("查找成功").data("item", admin);
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public R updateUser(@ApiParam(value = "用户", required = true)
                        @RequestBody Admin admin) {
        boolean result = adminService.updateById(admin);
        if (result) {
            System.out.println("chenggong");
            return R.ok().message("查找成功").data("item", admin);
        } else {
            return R.ok().message("未查找到用户");
        }
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable("id") int id) {
        int result = adminService.deleteById(id);
        if (result != 0) {
            return R.ok().message("删除用户成功");
        } else {
            return R.ok().message("删除用户失败");
        }
    }

    @ApiOperation("根据id列表删除")
    @DeleteMapping("/batchDelete")
    public R batchDelete(
            @ApiParam(value = "批量删除", required = true)
            @RequestBody List<Integer> idList) {
        boolean result = adminService.removeByIds(idList);
        if (result) {
            return R.ok().message("批量删除管理员成功");
        } else {
            return R.ok().message("批量删除管理员失败");
        }
    }

//    @ApiOperation("自动补全")
//    @GetMapping("/list/name/{key}")
//    public R selectNameListByKey(
//            @ApiParam(value = "关键字", required = true)
//            @PathVariable String key) {
//        List<Map<String, Object>> nameList = adminService.selectNameByKey(key);
//
//        return R.ok().data("nameList", nameList);
//    }
}
