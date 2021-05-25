package com.example.demo;

import com.example.demo.pojo.admin.vo.JwtInfo;
import com.example.demo.pojo.system.SysRole;
import com.example.demo.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.example.demo.util.JwtUtil.*;

@SpringBootTest
public class TokenTest {
    @Autowired
    AdminService adminService;

    @Test
    public void Token(){
        List<SysRole> roleSet = new List<SysRole>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<SysRole> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(SysRole sysRole) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends SysRole> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends SysRole> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public SysRole get(int index) {
                return null;
            }

            @Override
            public SysRole set(int index, SysRole element) {
                return null;
            }

            @Override
            public void add(int index, SysRole element) {

            }

            @Override
            public SysRole remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<SysRole> listIterator() {
                return null;
            }

            @Override
            public ListIterator<SysRole> listIterator(int index) {
                return null;
            }

            @Override
            public List<SysRole> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        roleSet = adminService.selectRolesById(1);
        System.out.println(roleSet);
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
