package com.multi.gradle.module.web.controllers;


import com.multi.gradle.module.business.Main.MainService;
import com.multi.gradle.module.core.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by dhokim on  2018-10-19
 */
@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String test(Model model){
        System.out.println("--------------------------------------");
        String token = Token.conToken();
        System.out.println("token : " + token);
        System.out.println("--------------------------------------");
        model.addAttribute("token",token);
        model.addAttribute("listCnt", mainService.getListCnt());
        return "test";
    }
    @GetMapping("/main")
    public String main(Model model){

        model.addAttribute("listCnt", mainService.getListCnt());
        return "main";
    }
}
