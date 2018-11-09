package com.multi.gradle.module.web.controllers;

import com.multi.gradle.module.core.utils.EncryptionService;
import com.multi.gradle.module.model.Json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EncryptionController {

    @Autowired
    EncryptionService encryptionService;

    @GetMapping("/encryption")
    public String encryption(){
        return "encryption";
    }

    @PostMapping("/encryption")
    @ResponseBody
    public JsonResponse postEncryption(@RequestParam("text")String text){
        System.out.println("암호화 시작 : " + text);
        JsonResponse json = new JsonResponse();
        json.setMessage("sucess");
        json.setResult(true);

        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(text);*/

        /*byte[] textByte = text.getBytes();
        System.out.println("result_1 : " + textByte);*/
        String encryptionText = encryptionService.encrypt(text);

        System.out.println(encryptionText);
        encryptionText = encryptionService.decrypt(encryptionText);
        System.out.println(encryptionText);

        return json;
    }

}
