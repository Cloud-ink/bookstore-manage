package com.example.demo.service;

import com.example.demo.pojo.user.User;
import com.example.demo.pojo.user.VoEmployee;
import com.example.demo.pojo.utils.VoPage;

import java.util.List;

public interface EmployeeService {
    public User addEmployee(User user);
    //public VoPage searchEmployees(int pageNo, int limit);
    //public VoPage searchEmployees(int pageNo, int limit);
//    public VoPage search(int pageNo, int limit, String idSorted);
    public User updateUser(User user);
    public int deleteUser(int user_id);
    public VoEmployee searchByName(String name);
}