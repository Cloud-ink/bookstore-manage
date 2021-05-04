package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.user.User;
import com.example.demo.pojo.user.vo.UserQueryVo;
import com.example.demo.pojo.user.vo.UserSaveVo;
import com.example.demo.pojo.utils.VoPage;
import com.example.demo.res.R;
import com.example.demo.res.Result;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("分页查询")
    @GetMapping("/list/{page}/{limit}")
    public R queryUser(@PathVariable("page") int page,
                       @PathVariable("limit") int limit,
                       @ApiParam("查询条件") UserQueryVo userQueryVo) {
        Page<User> pageParm = new Page<>(page, limit);
        IPage<User> pageModel = userService.selectPage(pageParm, userQueryVo);
        List<User> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        if (total == 0) {
            return R.ok().message("未找到用户");
        }
        return R.ok().data("total", total).data("items", records);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable("id") int id) {
        int result = userService.deleteById(id);
        if (result != 0) {
            return R.ok().message("删除用户成功");
        } else {
            return R.ok().message("删除用户失败");
        }
    }

    @ApiOperation("添加")
    @PostMapping("/save")
    public R addUser(@ApiParam(value = "用户对象", required = true)
                     @RequestBody UserSaveVo userVo) {
        userService.save(userVo);
        return R.ok().message("保存成功");
    }

    @ApiOperation("获取用户")
    @GetMapping("/get/{id}")
    public R getUser(@ApiParam(value = "用户id", required = true)
                     @PathVariable int id) {
        User user = userService.getById(id);
        if (user.equals(null)) {
            return R.ok().message("未查找到用户");
        } else {
            return R.ok().message("查找成功").data("item", user);
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public R updateUser(@ApiParam(value = "用户", required = true)
                        @RequestBody User user) {
        boolean result = userService.updateById(user);
        if (result) {
            return R.ok().message("查找成功").data("item", user);
        } else {
            return R.ok().message("未查找到用户");
        }
    }

    @ApiOperation("根据id列表删除")
    @DeleteMapping("/batchDelete")
    public R batchDelete(
            @ApiParam(value = "批量删除", required = true)
            @RequestBody List<Integer> idList) {
        boolean result = userService.removeByIds(idList);
        if (result) {
            return R.ok().message("批量删除用户成功");
        } else {
            return R.ok().message("批量删除用户失败");
        }
    }

    @ApiOperation("自动补全")
    @GetMapping("/list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String key) {
        List<Map<String, Object>> nameList = userService.selectNameByKey(key);

        return R.ok().data("nameList", nameList);
    }

}
