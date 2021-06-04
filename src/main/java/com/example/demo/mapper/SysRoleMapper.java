package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.system.SysRole;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> selectPermission(List<Long> idList);

    @ApiOperation("根据管理员id查找角色")
    SysRole findRoleByAdminId(int id);

    List<String> findPermissionsByRoleId(Long id);
}
