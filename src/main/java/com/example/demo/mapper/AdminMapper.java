package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    //用户名是否被注册
    boolean findUserName(String username);
    //注册
    boolean register(String user_name,String user_password);
    //根据用户名查找
    User findUserByUserName(String user_name);

    int deleteById(int id);

    int addUser(User user);

    int insertSelective(User user);

    User updateByKey(int id);

    int updataByPrimarySelective(User user);

    int updateUserById(User user);

    List<User> selectUser();

    List<User> selectUserById(int title);

}
