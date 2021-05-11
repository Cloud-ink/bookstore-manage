package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.user.User;
import com.example.demo.pojo.user.vo.UserQueryVo;
import com.example.demo.pojo.user.vo.UserSaveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.time.chrono.IsoEra;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    @ApiOperation("获取列表")
    List<User> getUserList();

    @ApiOperation("分页")
    IPage<User> selectPage(Page<User> page, UserQueryVo userQueryVo);

    @ApiOperation("删除")
    int deleteById(int id);

    @ApiOperation("添加")
    boolean save(UserSaveVo userVo);

    List<Map<String, Object>> selectNameByKey(String key);

}
