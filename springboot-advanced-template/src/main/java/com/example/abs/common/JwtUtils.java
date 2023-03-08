package com.example.abs.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * JWT跨域认证
 */
public class JwtUtils {
    //7天过期
    private static long expire = 604800;
    //32位秘钥
    private static String secret = "asj123saASDuo18v4SAD3A81d9iia90FXia18D1d23ads";

    //生成token
    public static String generateToken(String str) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(str)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //解析token 得Claims对象 需在调用getSubject()方法解析成String
    public static Claims getClaimsByToken(String token) throws Exception {
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public static String decodeByToken(String token) throws Exception {
        return getClaimsByToken(token).getSubject();
    }
}
