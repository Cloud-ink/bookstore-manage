package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.pojo.user.User;
import com.example.demo.service.SysRoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<String> selectPermission(List<Long> idList) {
        return sysRoleMapper.selectPermission(idList);
    }
}
