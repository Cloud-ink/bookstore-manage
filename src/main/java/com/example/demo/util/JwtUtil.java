package com.example.demo.util;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.pojo.admin.vo.JwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    public static final String TOKEN_LOGIN_NAME = "loginName";
    public static final String TOKEN_LOGIN_ID = "id";
    public static final String TOKEN_SUCCESS = "success:";
    public static final String TOKEN_FAIL = "fail:";

    private static final long EXPIRE_TIME = 30*60*1000;

    private static final String TOKEN_SECRET="j0ijsdfjlsjfljfl15135313135";

    //加密key
    public static Key getKetInstance(){
        //加密方式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //处理原始key
        byte[] bytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
        //返回加密后的key
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    //构建token
    public static String getToken(JwtInfo jwtInfo){
        //过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String,Object> header = new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        //附带username和userid生成签名
        String JwtToken = Jwts.builder()
                .setHeader(header)
                .setSubject("manage")
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("id", jwtInfo.getId())
                .claim("name", jwtInfo.getAdminName())
                .claim("avatar", jwtInfo.getAdminAvatar())
                .claim("role",jwtInfo.getAdminRole())
                .signWith(SignatureAlgorithm.HS256, getKetInstance())
                .compact();

        return JwtToken;
    }

    //判断token是否存在有效
    public static boolean checkToken(String token) {
        if(StringUtils.isEmpty(token)) return false;
        try {
            Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //检验重载，从request获取
    public static boolean checkToken(HttpServletRequest request){
        try{
            String token = request.getHeader("token");
            System.out.println(token);
            if(StringUtils.isEmpty(token)) return false;
            Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //解析jwt
    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request){
        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)) return null;

        System.out.println(StringUtils.isEmpty(token));

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(getKetInstance())
                .parseClaimsJws(token);//解析
        Claims claims = claimsJws.getBody();

        System.out.println("yi");

        System.out.println(Integer.parseInt(claims.get("id").toString()));
        System.out.println(claims.get("name").toString());
        System.out.println(claims.get("avatar").toString());
        System.out.println(claims.get("role").toString());
        JwtInfo jwtInfo = new JwtInfo(
                Integer.parseInt(claims.get("id").toString()),
                claims.get("name").toString(),
                claims.get("avatar").toString(),
                claims.get("role").toString());

        System.out.println("er");

        System.out.println(jwtInfo.getId());

        return jwtInfo;

    }

}
