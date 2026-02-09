package com.example.highway.utils;

import com.example.highway.pojo.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @title JwtTokenUtils
 * @Author: ZKY
 * @CreateTime: 2023-05-02  22:07
 * @Description: TODO
 */
public class JwtUtil {
    private static final long time = 1000*60*30;

    private static final String signature = "userLogin";
    /*
    * 生成JwkToken
    * */
    public static String generateJwtToken(User user){

        String Name = user.getName();
        String Id = String.valueOf(user.getId());
        JwtBuilder jwtBuilder = Jwts.builder();
        String Token = jwtBuilder
                //设置头信息
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //设置负载
                .claim("Name",Name)
                .claim("Id",Id)
                .setSubject("user: "+Id+" login")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //设置签名
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return Token;
    }

    /*
    * Token解密
    * */
    public static Claims JwtParse(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body;
    }
}
