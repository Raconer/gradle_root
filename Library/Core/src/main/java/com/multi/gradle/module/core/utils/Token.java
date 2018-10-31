package com.multi.gradle.module.core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Token {

    /**
     * @Name : conToken
     * @Date : 2018. 10. 31.
     * @Author : dhokim
     * @Comment : Convert for Token
     * @param
     * @return
     */
    public static String conToken() {
        String jwt = "";
        try {
            jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(new Date())
                    .claim("name", "Robert Token Man")
                    .claim("scope", "self groups/admins")
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();

        }catch (Exception e){
            e.getMessage();
        }
        System.out.println("jwt : " + jwt);
        return jwt;
    }
}

// assert 불리언식; or assert 불리언식:수식;
// ex) assert i < 0; or assert age > 0 : "나이는 음수가 될 수 없습니다:"+age;
// i가 0보다 클경우 AssertionError발생
// age가 0보다 작을 경우 AssertionError발생, 그때 AssertionError의 예외 메시지는 "나이는 음수가 될수 없습니다:age" 출력
// 따라서 assert 의 불리언 식이 false가 생겼을시 예외 발생
// 하단 try catch 문과 비슷하다
// assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");