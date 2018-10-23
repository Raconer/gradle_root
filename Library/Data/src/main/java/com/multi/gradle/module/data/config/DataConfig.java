package com.multi.gradle.module.data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dhokim on  2018-10-22
 */
@Configuration
@ComponentScan({"com.multi.gradle.module.model","com.multi.gradle.module.data"})
public class DataConfig {
}
