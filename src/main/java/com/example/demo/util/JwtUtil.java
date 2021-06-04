package com.example.demo.util;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.pojo.admin.vo.JwtAdminInfo;
import com.example.demo.pojo.system.SysRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    private static final String TOKEN_SECRET = "j0ijsdfjlsjfljfl15135313135";

    private static final String ROLE_CLAIMS = "rol";

    //加密key
    public static Key getKetInstance() {
        //加密方式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //处理原始key
        byte[] bytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
        //返回加密后的key
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    //构建token
    public static String createToken(JwtAdminInfo jwtAdminInfo) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //设置role
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, jwtAdminInfo.getRole());
        //附带username和userid生成签名
        String token = Jwts.builder()
                .setHeader(header)
                .setSubject("manage")
                .setIssuedAt(new Date())
                .setExpiration(date)
                .setClaims(map)
                .claim("id", jwtAdminInfo.getId())
                .claim("name", jwtAdminInfo.getAdminName())
                .claim("avatar", jwtAdminInfo.getAdminAvatar())
                .claim("permissions", jwtAdminInfo.getPermissions())
                .signWith(SignatureAlgorithm.HS256, getKetInstance())
                .compact();

        return token;
    }

    //判断token是否存在有效
    public static boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) return false;
        try {
            Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //检验重载，从request获取
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            System.out.println(token);
            if (StringUtils.isEmpty(token)) return false;
            Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //解析jwt
//    public static JwtAdminInfo getMemberIdByJwtToken(HttpServletRequest request) {
//        String token = request.getHeader("token");
//
//        if (StringUtils.isEmpty(token)) return null;
//
//        Jws<Claims> claimsJws = Jwts.parser()
//                .setSigningKey(getKetInstance())
//                .parseClaimsJws(token);//解析
//
//        Claims claims = claimsJws.getBody();
//
//        JwtAdminInfo jwtAdminInfo = new JwtAdminInfo(
//                Integer.parseInt(claims.get("id").toString()),
//                claims.get("name").toString(),
//                claims.get("avatar").toString(),
//                claims.get("role").toString(),
//                (List<String>) claims.get("permissions"),
//                Collection);
//
//        return jwtAdminInfo;
//
//    }

    public static String getType(Object object) {
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        return type;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public static JwtAdminInfo getAdmin(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token).getBody();

        Integer id = Integer.parseInt(claims.get("id").toString());
        String name = claims.get("name").toString();
        String avatar = claims.get("avatar").toString();
        String role = claims.get("rol").toString();

        JwtAdminInfo jwtAdminInfo = new JwtAdminInfo();
        jwtAdminInfo.setId(id);
        jwtAdminInfo.setAdminName(name);
        jwtAdminInfo.setAdminAvatar(avatar);
        jwtAdminInfo.setRole(role);

        return jwtAdminInfo;
    }

    public static String getAdminId(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token).getBody();
        System.out.println(claims.get("name").toString());
        return claims.get("name").toString();
    }

    public static String getAdminName(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token).getBody();
        System.out.println(claims.get("id").toString());
        return claims.get("id").toString();
    }

    public static String getAdminAvatar(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token).getBody();
        System.out.println(claims.get("avatar").toString());
        return claims.get("avatar").toString();
    }

    public static String getAdminRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKetInstance()).parseClaimsJws(token).getBody();
        System.out.println(claims.get("rol").toString());
        return claims.get("rol").toString();
    }


}
