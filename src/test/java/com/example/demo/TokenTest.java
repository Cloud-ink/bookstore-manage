package com.example.demo;

import com.example.demo.pojo.admin.vo.JwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo.utils.JwtUtil.*;

@SpringBootTest
public class TokenTest {
    @Test
    public void Token(){
        JwtInfo jwtInfo = new JwtInfo(1, "lili", "tupian", "quanxian");
        String token = getToken(jwtInfo);
        System.out.println(token);
        JwtInfo info = getMemberId(token);
        System.out.println(info.getId()+"/"+info.getAdminName());
    }

    public static JwtInfo getMemberId(String token){
        if(StringUtils.isEmpty(token)) return null;

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(getKetInstance())
                .parseClaimsJws(token);//解析
        Claims claims = claimsJws.getBody();

        JwtInfo jwtInfo = new JwtInfo(
                Integer.parseInt(claims.get("id").toString()),
                claims.get("name").toString(),
                claims.get("avatar").toString(),
                claims.get("role").toString());

        System.out.println(jwtInfo.getId());

        return jwtInfo;

    }

}
