package com.multi.gradle.module.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by dhokim on  2018-10-23
 */
@Configuration
// WebMvcConfigurerAdapter이 뭘까?
public class TilesConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public TilesConfigurer tilesConfigurer(){

        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
        configurer.setCheckRefresh(true);
        return configurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
}
