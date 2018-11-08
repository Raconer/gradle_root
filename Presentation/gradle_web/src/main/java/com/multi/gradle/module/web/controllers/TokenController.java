package com.multi.gradle.module.web.controllers;

import com.multi.gradle.module.core.utils.TokenService;
import com.multi.gradle.module.model.Json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/token")
    public String token(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String name = "";
        String value = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("jwt")){
                    name = cookie.getName();
                    value = cookie .getValue();
                }
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("value", value);

        return "token";
    }

    /**
      * @RequestBody 어노테이션이란? HTTP 요청의 body 내용을 자바 객체로 매핑하는 역할을 합니다.
      * @ResponseBody 어노테이션이란? 자바 객체를 HTTP 요청의 body 내용으로 매핑하는 역할을 합니다.
      **/

    /** 토큰 생성 */
    @PostMapping("/token/generate")
    @ResponseBody
    public JsonResponse generToken(HttpServletResponse response){
        JsonResponse json = new JsonResponse();
        json.setMessage("faild");
        json.setResult(false);

        String token = tokenService.conToken();
        System.out.println("Make Token : " + token);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setComment("사용자 토큰");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        json.setData(token);
        json.setMessage("sucess");
        json.setResult(true);

        return json;
    }


    /** 토큰 체크 */
    @PostMapping("/token/chk")
    @ResponseBody
    public JsonResponse chkToken(@RequestParam String token){
        JsonResponse json = new JsonResponse();
        json.setMessage("faild");
        json.setResult(false);

        boolean chkToken = tokenService.chkToken(token) != null? true : false;
        System.out.println("Make Token : " + (chkToken ? "사용 가능 토큰":"사용 불가능 토큰"));

        json.setData(token);
        json.setMessage("sucess");
        json.setResult(true);

        return json;
    }

    /** Refresh 토큰 재생성*/
    @PostMapping("/token/refresh")
    @ResponseBody
    public JsonResponse refreshToken(@RequestParam("token") String token){
        System.out.println("refresh start");
        JsonResponse json = new JsonResponse();
        json.setMessage("faild");
        json.setResult(false);
        token = tokenService.refreshToken(token);
        System.out.println("Refresh Token : " + token);

        json.setData(token);
        json.setMessage("sucess");
        json.setResult(true);
        return json;
    }

    /** Refresh 토큰 파기*/
    @PostMapping("/token/expire")
    @ResponseBody
    public JsonResponse expireToken(@RequestParam String token){
        JsonResponse json = new JsonResponse();
        json.setMessage("faild");
        json.setResult(false);

        String expireToken = tokenService.expireToken(token);

        json.setData(expireToken);
        json.setMessage("sucess");
        json.setResult(true);

        return json;
    }

}
