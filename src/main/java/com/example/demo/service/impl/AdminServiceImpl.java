package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.AdminVo;
import com.example.demo.pojo.admin.vo.JwtAdminInfo;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.security.auth.login.LoginException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    //登录
    @Override
    public Admin verity(Admin adminVo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_name", adminVo.getAdminName());
        queryWrapper.eq("admin_password", adminVo.getAdminPassword());
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public boolean findAdmin(Admin adminVo) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_name", adminVo.getAdminName());
        queryWrapper.eq("admin_password", adminVo.getAdminPassword());
        int result = adminMapper.selectCount(queryWrapper);

        if(result == 0) return false;

        return true;
    }

    @Override
    public List<SysRole> selectRolesById(int id) {

        return adminMapper.selectRolesById(id);
    }

    //验证用户名和密码是否符合规范
    public boolean validateLogin(String username,String pwd) {
        boolean con = false;    // 用户名和密码是否正确
        boolean conUname = false;    // 用户名格式是否正确
        try {
            if (username.length() >= 6 && username.length() <= 10) {
                for (int i = 0;i < username.length();i++) {
                    char ch = username.charAt(i);    // 获取每一个字符
                    if (ch >= '0' && ch <= '9') {    // 判断字符是否为0~9的数字
                        conUname = true;    // 设置 conUname 变量值为 true
                    } else {
                        // 如果字符不是0~9的数字，则拋出LoginException异常
                        conUname = false;
                        throw new LoginException("用户名中包含有非数字的字符！");
                    }
                }
            } else {
                // 如果用户名长度不在6~10位之间，拋出异常
                throw new LoginException("用户名长度必须在6〜10位之间！");
            }
            if (conUname) {    // 如果用户名格式正确，判断密码长度
                if (pwd.length() == 6) {
                    // 如果密码长度等于6
                    con=true;    // 设置con变量的值为true，表示登录信息符合要求
                } else {    // 如果密码长度不等于6，拋出异常
                    con = false;
                    throw new LoginException("密码长度必须为 6 位！");
                }
            }
        } catch(LoginException e) {
            // 捕获 LoginException 异常
            System.out.println(e.getMessage());
        }
        return con;
    }

    @ApiOperation("分页")
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, JwtAdminInfo jwtAdminInfo) {
        //显示分页查询列表
        //1.排序，按sort字段排序
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        //queryWrapper.orderByAsc("sort");

        //2.分页查询
        if(jwtAdminInfo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        //3.条件查询
        String userName = jwtAdminInfo.getAdminName();
        String userPhone = jwtAdminInfo.getAdminAvatar();

        if(userPhone != null){
            queryWrapper.eq("user_phone", userPhone);
        }

        if(!StringUtils.isEmpty(userName)){
            queryWrapper.likeRight("user_name", userName);
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean save(AdminVo adminVo) {
        Admin admin = new Admin();
        admin.setAdminPhone(adminVo.getAdminPhone());
        admin.setAdminName(adminVo.getAdminName());
        admin.setAdminPassword(adminVo.getAdminPassword());
        admin.setAdminAvatar(adminVo.getAdminAvatar());
        int result = adminMapper.insert(admin);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @ApiOperation("删除")
    @Override
    public int deleteById(int id) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return adminMapper.delete(queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name", adminName);
        Admin admin = this.adminMapper.selectOne(wrapper);
        if (admin == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        JwtAdminInfo jwtAdminInfo = new JwtAdminInfo();
        jwtAdminInfo.setId(admin.getId());
        jwtAdminInfo.setAdminName(admin.getAdminName());
        jwtAdminInfo.setPassword(admin.getAdminPassword());
        jwtAdminInfo.setAdminAvatar(admin.getAdminAvatar());
        jwtAdminInfo.setRole(admin.getRole());
        return jwtAdminInfo;
    }

    @Transactional
    @Override
    public JwtAdminInfo findByUsername(String username) {
        Admin admin = adminMapper.findAdminByAdminName(username);
        if (admin != null) {
            JwtAdminInfo jwtAdminInfo = new JwtAdminInfo();
            BeanUtils.copyProperties(admin, jwtAdminInfo);

            SysRole role = sysRoleMapper.findRoleByAdminId(admin.getId());
            jwtAdminInfo.setRole(role);// 设置角色

            List<String> permissions = sysRoleMapper.findPermissionsByRoleId(role.getId());
            Collection<GrantedAuthority> authorities = new HashSet<>();
            if(permissions != null) {
                for (String permission: permissions) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }

            }

            
            return loginAppUser;
        }
        return null;
    }

//    @ApiOperation("添加")
//    @Override
//    public boolean save(UserSaveVo userVo) {
//
//        User user = new User();
//        user.setUserPhone(userVo.getUserPhone());
//        user.setUserName(userVo.getUserName());
//        user.setUserAddress(userVo.getUserAddress());
//        user.setUserEmail(userVo.getUserEmail());
//        user.setUserPassword(userVo.getUserPassword());
//        user.setUserGender(userVo.getUserGender());
//
//        int result = userMapper.insert(user);
//        if (result != 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public List<Map<String, Object>> selectNameByKey(String key) {
//
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("user_name");
//        queryWrapper.likeRight("user_name", key);
//
//        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
//        return list;
//    }
    
}
