package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.user.User;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    //登录
    @Override
    public Admin verity(Admin adminVo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_name", adminVo.getAdminName());
        queryWrapper.eq("admin_password", adminVo.getAdminPassword());
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    //登录
    @Override
    public User findUserByUserName(String username) {
        User user = adminMapper.findUserByUserName(username);
        return user;
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

    
}
