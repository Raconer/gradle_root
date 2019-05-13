package com.multi.gradle.module.web.controllers;


import com.multi.gradle.module.business.Main.MainService;
import com.multi.gradle.module.web.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by dhokim on  2018-10-19
 */
@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String test(Model model){
        System.out.println("Main Controller test Method");
        String regexTest1 = "https://m.blog.naver.com/PostView.nhn?blogId=syung1104&logNo=220847918251&proxyReferer=https%3A%2F%2Fwww.google.com%2F";
        String regexTest2 = "http://m.blog.naver.com/PostView.nhn?blogId=syung1104&logNo=220847918251&proxyReferer=https%3A%2F%2Fwww.google.com%2F";
        String regexTest3 = "htt://m.blog.naver.com/PostView.nhn?blogId=syung1104&logNo=220847918251&proxyReferer=https%3A%2F%2Fwww.google.com%2F";
        String regexTest4 = "https://m.blog.naver.https://.com/PostView.nhn?blogId=syung1104&logNo=220847918251&proxyReferer=https%3A%2F%2Fwww.google.com%2F";
        String regexTest5 = "ttps://m.blog.naver.com/PostView.nhn?blogId=syung1104&logNo=220847918251&proxyReferer=https%3A%2F%2Fwww.google.com%2F";
        String isRegex = "http[s]?://(.*)";
        System.out.println("isRegex_1 : " + regexTest1.matches(isRegex));
        System.out.println("isRegex_2 : " + regexTest2.matches(isRegex));
        System.out.println("isRegex_3 : " + regexTest3.matches(isRegex));
        System.out.println("isRegex_4 : " + regexTest4.matches(isRegex));
        System.out.println("isRegex_5 : " + regexTest5.matches(isRegex));

        return "test";
    }

    @GetMapping("/main")
    public String main(Model model){

        model.addAttribute("listCnt", mainService.getListCnt());
        return "main";
    }

    @PostMapping("/main/test")
    public String mainTest(@ModelAttribute("TestModel") TestModel testModel){
        System.out.println(testModel.toString());
        return "main";
    }
}
