package com.multi.gradle.module.core.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 ClaimJwtException       : JWT권한 claim검사가 실패 했을대
 ExpiredJwtException     : 유효 기간이 지난 JWT를 수신한 경우
 MalformedJwtException   : 구조적인 문제가 있는 JWT인 경우
 PrematureJwtException   : 접근이 허용되기 전인 JWT가 수신된 경우
 SignatureException      : 시그니처 연산이 실패 하거나, JWT의 시그너처 검증이 실패한 경우
 UnsupportedJwtException : 수신한 JWT의 형식이 애플리케이션에서 원하는 형식과 맞지 않은 경우
 예를 들면, 암호화된 JWT를 사용하는 애플리케이션에 암호화되지 않은 JWT가 전달되는 경우에 이예외 발생
 */

/**
 assert 불리언식; or assert 불리언식:수식;
 ex) assert i < 0; or assert age > 0 : "나이는 음수가 될 수 없습니다:"+age;
 i가 0보다 클경우 AssertionError발생
 age가 0보다 작을 경우 AssertionError발생, 그때 AssertionError의 예외 메시지는 "나이는 음수가 될수 없습니다:age" 출력
 따라서 assert 의 불리언 식이 false가 생겼을시 예외 발생
 하단 try catch 문과 비슷하다
 assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
 */
@Service
public class Token {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    /***
     *  1. conToken     : 토큰생성
     *  2. chkToken     : 토큰 사용 체크
     *  3. refreshToken : 토큰 갱신(기간 연장) _ 개발
     *  4. expireToken  : 토큰 파기 _ 개발
     * */

    /**
      * 토큰 생성
      **/
    public String conToken() {
        Date expire = new Date(new Date().getTime() + 3000000);
        String jwt = "";

        System.out.println(sdf.format(expire));

        try {
            jwt = Jwts.builder()
                    // 사용자 데이터
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(expire)
                    .claim("name", "Robert Token Man")
                    .claim("scope", "self groups/admins")
                    .signWith(
                            SignatureAlgorithm.HS256, // 헤더 알고리즘
                            "secret".getBytes("UTF-8") // signature
                    )
                    .compact();

        }catch (UnsupportedEncodingException e){
            System.out.println("서명에 문제가 있습니다");
            //e.getMessage();
            return null;
        }
        return jwt;
    }

    /**
      * 토큰 사용 체크
      **/
    public boolean chkToken(String token){

        String jwt = token;

        try {
            Jws<Claims> claims = Jwts.parser()
                            .setSigningKey("secret".getBytes("UTF-8"))
                            .parseClaimsJws(jwt);// 서명 오류를 구별한다.

            System.out.println("Token Check Data : " + tokenData(claims));
            // assertEquals(scope, "self groups/admins");
        }catch (ExpiredJwtException e){ // ClaimJwtException 같은 Exception
            System.out.println("토큰 기간이 만료 되었습니다");
            //e.getMessage();
            return false;
        }catch (MalformedJwtException e){
            System.out.println("서명에 문제가 있습니다_3");
            //e.getMessage();
            return false;
        }catch (PrematureJwtException e){ // ClaimJwtException 같은 Exception
            System.out.println("서명에 문제가 있습니다_4");
            //e.getMessage();
            return false;
        }catch (SignatureException e){
            System.out.println("signingKey 가 다릅니다");
            //e.getMessage();
            return false;
        }catch (UnsupportedJwtException e){
            System.out.println("서명에 문제가 있습니다_6");
            //e.getMessage();
            return false;
        }catch (Exception e){
            System.out.println("서명에 문제가 있습니다_7");
            //e.getMessage();
            return false;
        }

        return true;
    }

    /**
      * 토큰 갱신
      **/
    public String refreshToken(String token){
        if(!chkToken(token)){
            return "";
        }
        Date expire = new Date(new Date().getTime() + 3000000);

        System.out.println("refresh Expired" + sdf.format(expire));

        String refreshToken = expiredChgToken(token, expire);

        return refreshToken;
    }

    /**
      * 토큰 파기
      **/
    public String expireToken(String token){

        Date date = new Date();
        System.out.println("expired Expired" + sdf.format(date));
        String refreshToken = expiredChgToken(token, date);

        return refreshToken;
    }

    /**
      * 토큰 기간 변경
      **/
    private String expiredChgToken(String token, Date date){

        String refreshToken = "";

        try{
            final Claims claims = getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
                    .compact();
        }catch (Exception e){
            e.getMessage();
            return "";
        }
        return refreshToken;
    }

    /**
      * 토큰 상세 데이터 String 으로 변환
      **/
    private String tokenData(Jws<Claims> claims){

        String scope = claims.getBody().toString();

        return scope;
    }

    /**
      * 토큰 상세 데이터를 Claims화
      **/
    private Claims getAllClaimsFromToken(String token) throws Exception{
        return Jwts.parser()
                    .setSigningKey("secret".getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
    }
}

