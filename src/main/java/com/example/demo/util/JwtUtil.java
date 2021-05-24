package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.pojo.admin.vo.JwtInfo;
import com.example.demo.pojo.font.Collect;
import com.example.demo.pojo.system.SysRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class JwtUtil {

    public static final String TOKEN_LOGIN_NAME = "loginName";
    public static final String TOKEN_LOGIN_ID = "id";
    public static final String TOKEN_SUCCESS = "success:";
    public static final String TOKEN_FAIL = "fail:";

    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    private static final String TOKEN_SECRET = "j0ijsdfjlsjfljfl15135313135";

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
    public static String getToken(JwtInfo jwtInfo) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userid生成签名
        String JwtToken = Jwts.builder()
                .setHeader(header)
                .setSubject("manage")
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("id", jwtInfo.getId())
                .claim("name", jwtInfo.getAdminName())
                .claim("avatar", jwtInfo.getAdminAvatar())
                .claim("roles", jwtInfo.getRoles())
                .claim("permissions", jwtInfo.getPermissions())
                .signWith(SignatureAlgorithm.HS256, getKetInstance())
                .compact();

        return JwtToken;
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
    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) return null;

        System.out.println(StringUtils.isEmpty(token));

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(getKetInstance())
                .parseClaimsJws(token);//解析
        Claims claims = claimsJws.getBody();

        String avatar = (String) claims.get("avatar");
        Object obj = claims.get("roles");
        Set<SysRole> set = (Set<SysRole>) obj;
        Object objP = claims.get("permissions");
        Set<String> setP = (Set<String>) objP;
        JwtInfo jwtInfo = new JwtInfo(
                Integer.parseInt(claims.get("id").toString()),
                claims.get("name").toString(),
                claims.get("avatar").toString(),
                set,
                setP);

        System.out.println(jwtInfo.getId());
        System.out.println(jwtInfo.getRoles());
        System.out.println(jwtInfo.getPermissions());

        return jwtInfo;

    }

    public static String getType(Object object) {
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        return type;
    }
}
