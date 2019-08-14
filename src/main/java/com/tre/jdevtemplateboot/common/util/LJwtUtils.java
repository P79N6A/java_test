package com.tre.jdevtemplateboot.common.util;

import com.tre.jdevtemplateboot.common.constant.SysConstantJwToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: JWT token工厂
 * @author: JDev
 * @create: 2018-12-10 08:39
 **/
public class LJwtUtils {

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("created", new Date());
        return generateToken((claims));
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)        //payload
                //.setExpiration(new Date(System.currentTimeMillis() + SysConstantJwt.JWT_TTL))  //过期时间
                .signWith(SignatureAlgorithm.HS512, SysConstantJwToken.JWT_SECERT).compact();
    }
}
