package com.example.demo;

import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.admin.vo.JwtAdminInfo;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.pojo.user.User;
import com.example.demo.service.AdminService;
import com.example.demo.util.JwtUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TokenTest {
    @Autowired
    AdminService adminService;

    @Test
    public void Token(){
        JwtAdminInfo jwtAdminInfo = new JwtAdminInfo();
        jwtAdminInfo.setId(1);
        jwtAdminInfo.setAdminName("aaa");
        jwtAdminInfo.setAdminAvatar("ssss");
        jwtAdminInfo.setRole("dddd");
        List list = new ArrayList() ;
        list.add("asd");
        jwtAdminInfo.setPermissions(list);
        String token = JwtUtil.createToken(jwtAdminInfo);
        JwtUtil.getAdminId(token);
        JwtUtil.getAdminAvatar(token);
        JwtUtil.getAdminName(token);
        JwtUtil.getAdminRole(token);
        System.out.println(JwtUtil.createToken(jwtAdminInfo));
    }

//    public static JwtInfo getMemberId(String token){
//        if(StringUtils.isEmpty(token)) return null;
//
//        Jws<Claims> claimsJws = Jwts.parser()
//                .setSigningKey(getKetInstance())
//                .parseClaimsJws(token);//解析
//        Claims claims = claimsJws.getBody();
//
//        JwtInfo jwtInfo = new JwtInfo(
//                Integer.parseInt(claims.get("id").toString()),
//                claims.get("name").toString(),
//                claims.get("avatar").toString(),
//                claims.get("role").toString());
//
//        System.out.println(jwtInfo.getId());
//
//        return jwtInfo;
//
//    }

}
