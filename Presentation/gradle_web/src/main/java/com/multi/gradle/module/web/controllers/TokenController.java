package com.multi.gradle.module.web.controllers;

import com.multi.gradle.module.core.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TokenController {

    @Autowired
    Token tokenService;

    @GetMapping("/token")
    public String token(){

        System.out.println("--------------------------------------");

        /** 토큰 생성 */
        String token = tokenService.conToken();
        System.out.println("Make Token : " + token);
        /** 토큰 체크 */
        boolean chkToken = tokenService.chkToken(token);
        System.out.println("Make Token : " + (chkToken ? "사용 가능 토큰":"사용 불가능 토큰"));
        /** Refresh 토큰 생성*/
        token = tokenService.refreshToken(token);
        System.out.println("Refresh Token : " + token);

        /** Refresh 토큰 체크*/
        chkToken = tokenService.chkToken(token);
        System.out.println("Make Token : " + (chkToken ? "사용 가능 토큰":"사용 불가능 토큰"));

        System.out.println("--------------------------------------");

        return "token";
    }
}
