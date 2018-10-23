package com.multi.gradle.module.web.controllers;


import com.multi.gradle.module.business.Main.MainService;
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
    public String main(){
        return "main";
    }
}
