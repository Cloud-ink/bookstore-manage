package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.AdminVo;
import com.example.demo.pojo.admin.vo.JwtAdminInfo;
import com.example.demo.pojo.system.SysRole;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends IService<Admin>, UserDetailsService {
    //如果验证成功，返回user对象，失败返回null或异常
    Admin verity(Admin admin);
    //根据用户名查找
    boolean findAdmin(Admin admin);

    //根据用户id查询用户角色
    List<SysRole> selectRolesById(int id);

    @ApiOperation("分页")
    IPage<Admin> selectPage(Page<Admin> page, JwtAdminInfo jwtAdminInfo);

    @ApiOperation("添加")
    boolean save(AdminVo adminVo);

    @ApiOperation("删除")
    int deleteById(int id);

    public JwtAdminInfo findByUsername(String username);
}
