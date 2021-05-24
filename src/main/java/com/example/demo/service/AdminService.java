package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.pojo.user.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AdminService extends IService<Admin> {
    //如果验证成功，返回user对象，失败返回null或异常
    Admin verity(Admin admin);
    //根据用户名查找
    boolean findAdmin(Admin admin);

    //根据用户id查询用户角色
    Set<SysRole> selectRolesById(int id);

    //根据用户id查询用户权限
    Set<String> selectPermission(int id);

    Set<Long> selectRolesId(Set<SysRole> roleSet);
}
