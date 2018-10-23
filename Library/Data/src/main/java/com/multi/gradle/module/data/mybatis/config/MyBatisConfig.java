package com.multi.gradle.module.data.mybatis.config;

import com.multi.gradle.module.data.mybatis.core.DefaultMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by dhokim on  2018-10-22
 */
public class MyBatisConfig {

    // Mapper Interface 위치
    public static final String BASE_PACKAGE = "com.multi.gradle.module.data.mybatis.repository";
    // Data Model 위치
    public static final String TYPE_ALIASES_PACKAGE = "com.multi.gradle.module.model";
    // 기본 configuration setting 위치
    public static final String CONFIG_LOCATION_PATH = "classpath:META-INF/mybatis/mybatis-config.xml";
    // 명령어 쿼리 위치
    public static final String MAPPER_LOCATIONS_PATH = "classpath:META-INF/mybatis/mapper/**/*.xml";

    protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException{
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
    }
}



@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = DefaultMapper.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
class MasterMyBatisConfig extends MyBatisConfig {

    @Bean
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier(MasterDatabaseConfig.QUALIFIER) DataSource masterDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, masterDataSource);
        return sessionFactoryBean.getObject();
    }
}