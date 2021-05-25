package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.system.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    //根据用户id查询用户权限
    List<String> selectPermission(List<Long> idList);
}
