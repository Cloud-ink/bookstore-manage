package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.user.User;
import com.example.demo.pojo.user.vo.UserQueryVo;
import com.example.demo.pojo.user.vo.UserSaveVo;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        return userMapper.selectList(queryWrapper);
    }

    @ApiOperation("分页")
    @Override
    public IPage<User> selectPage(Page<User> pageParam, UserQueryVo userQueryVo) {
        //显示分页查询列表
        //1.排序，按sort字段排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        //2.分页查询
        if(userQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        //3.条件查询
        Long id = userQueryVo.getId();
        String userName = userQueryVo.getUserName();
        Integer userPhone = userQueryVo.getUserPhone();
        String userGender = userQueryVo.getUserGender();

        if(id != 0){
            queryWrapper.eq("id", id);
        }

        if(userPhone != 0){
            queryWrapper.eq("user_phone", userPhone);
        }

        if(!StringUtils.isEmpty(userName)){
            queryWrapper.likeRight("user_name", userName);
        }

        if(!StringUtils.isEmpty(userGender)){
            queryWrapper.likeRight("user_gender", userGender);
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @ApiOperation("删除")
    @Override
    public int deleteById(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.delete(queryWrapper);
    }

    @ApiOperation("添加")
    @Override
    public boolean save(UserSaveVo userVo) {

        User user = new User();
        user.setUserPhone(userVo.getUserPhone());
        user.setUserName(userVo.getUserName());
        user.setUserAddress(userVo.getUserAddress());
        user.setUserEmail(userVo.getUserEmail());
        user.setUserPassword(userVo.getUserPassword());
        user.setUserGender(userVo.getUserGender());

        int result = userMapper.insert(user);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> selectNameByKey(String key) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name");
        queryWrapper.likeRight("user_name", key);

        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        return list;
    }

//    @ApiOperation("根据id获取用户")
//    @Override
//    public boolean getUser(UserSaveVo userVo) {
//
////        User user = new User();
////        user.setUserPhone(userVo.getUserPhone());
////        user.setUserName(userVo.getUserName());
////        user.setUserAddress(userVo.getUserAddress());
////        user.setUserEmail(userVo.getUserEmail());
////        user.setUserPassword(userVo.getUserPassword());
////        user.setUserGender(userVo.getUserGender());
////
////        int result = userMapper.update(user, );
//        if (result != 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
