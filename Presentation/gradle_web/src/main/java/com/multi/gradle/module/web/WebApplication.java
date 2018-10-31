package com.multi.gradle.module.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by dhokim on  2018-10-19
 */
@SpringBootApplication
@ComponentScan({"com.multi.gradle.module.web", "com.multi.gradle.module.business", "com.multi.gradle.module.core"})
public class WebApplication  {
    public static void main(String[] args){
       SpringApplication.run(WebApplication.class, args);

       /* SpringApplicationBuilder builder = new SpringApplicationBuilder(WebApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);*/
    }
}