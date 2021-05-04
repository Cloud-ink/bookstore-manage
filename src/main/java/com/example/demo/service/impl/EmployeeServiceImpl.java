package com.example.demo.service.impl;

import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.user.User;
import com.example.demo.pojo.user.VoEmployee;
import com.example.demo.pojo.utils.VoPage;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private AdminMapper adminMapper;

    private Map<Integer, VoEmployee> idMaps = new TreeMap<>();
    private Map<String, VoEmployee> userNameMaps = new HashMap<>();
    //添加用户
    @Override
    public User addEmployee(User user) {
        int u = adminMapper.addUser(user);
        return user;
    }

//    @Override
//    public VoPage searchEmployees(int pageNo, int limit) {
//        VoPage page = new VoPage();
//        List<User> list = new ArrayList<>();
//        // if(title == 0){
////            list.addAll(usersMapper.selectUser());
////            int total = usersMapper.selectUser().size();
//        list.addAll(adminMapper.selectUserById(title));
//        int total = adminMapper.selectUserById(title).size();
//        page.setItems(list);
//        page.setTotal(total);
//        //    }else{
////            list.addAll(usersMapper.selectUserById());
////            int total = usersMapper.selectUserById().size();
////            page.setItems(list);
////            page.setTotal(total);
//
//        //}
//        return page;
//        //return usersMapper.selectUser();
//    }
//    //查询所有用户信息
//    @Override
//    public VoPage searchEmployees(int pageNo, int limit) {
//        VoPage page = new VoPage();
//        List<User> list = new ArrayList<>();
//       // if(title == 0){
////            list.addAll(usersMapper.selectUser());
////            int total = usersMapper.selectUser().size();
//        list.addAll(adminMapper.selectUser());
//        int total = adminMapper.selectUser().size();
//            page.setItems(list);
//            page.setTotal(total);
//        //    }else{
////            list.addAll(usersMapper.selectUserById());
////            int total = usersMapper.selectUserById().size();
////            page.setItems(list);
////            page.setTotal(total);
//
//        //}
//        return page;
//        //return usersMapper.selectUser();
//    }

    //更新用户信息
    @Override
    public User updateUser(User user) {
        int u = adminMapper.updateUserById(user);
        return user;
    }

    //删除用户信息
    @Override
    public int deleteUser(int user_id) {
        return adminMapper.deleteById(user_id);
    }

//    @Override
//    public VoPage search(int pageNo, int limit, String idSorted) {
//        VoPage page = new VoPage();
//        List<User> list = new ArrayList<>();
//        // if(title == 0){
////            list.addAll(usersMapper.selectUser());
////            int total = usersMapper.selectUser().size();
//        list.addAll(usersMapper.selectUser());
//        int total = usersMapper.selectUser().size();
//        page.setItems(list);
//        page.setTotal(total);
//        //    }else{
////            list.addAll(usersMapper.selectUserById());
////            int total = usersMapper.selectUserById().size();
////            page.setItems(list);
////            page.setTotal(total);
//
//        //}
//        return page;
//        //return usersMapper.selectUser();
//    }

    @Override
    public VoEmployee searchByName(String userName) {
        return this.userNameMaps.get(userName);
    }

}
