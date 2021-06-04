package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    //用户名是否被注册
    boolean findUserName(String username);

    //注册
    boolean register(String user_name,String user_password);

    //根据用户名查找
    Admin findAdminByAdminName(String name);

    int deleteById(int id);

    //连表查询，查询用户的角色
    List<SysRole> selectRolesById(int id);

    //连表查询，查询用户权限
    List<String> selectPermission(List<Long> idList);
}
