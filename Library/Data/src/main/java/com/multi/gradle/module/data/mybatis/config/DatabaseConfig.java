package com.multi.gradle.module.data.mybatis.config;

import com.multi.gradle.module.model.properties.IMybatisProperties;
import com.multi.gradle.module.model.properties.MybatisMasterProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by dhokim on  2018-10-19
 */

public abstract class DatabaseConfig{
    @Bean
    public abstract DataSource dataSource();

    protected  void configureDataSource(HikariConfig config, IMybatisProperties databaseProperties){
        System.out.println("databaseProperties : " + databaseProperties.toString());
        config.setDriverClassName(databaseProperties.getDriver());
        config.setJdbcUrl(databaseProperties.getUrl());
        config.setUsername(databaseProperties.getUsername());
        config.setPassword(databaseProperties.getPassword());

        // 기본적으로 캐시가 실제로 사용불가능한 경우 위의 매개 변수 중 어느 것도 영향을 주지 않는다
        config.addDataSourceProperty("cachePrepStmts", "true");
        // MySql 드라이버가 연결 당 캐시 할 준비된 명령문의 수를 설정한다.(250-500사이로 설정하는 것이 좋다)
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        // 드라이버가 캐시 할 준비가 된 SQL 문의 최대 길이입니다.(MySql에서 default 값은 256 이다)
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        // 최신 버전의 MySQL은 서버 측 준비 문을 지원하므로 상당한 성능 향상을 제공 할 수 있습니다.
        config.addDataSourceProperty("useServerPropStmts", "true");

        config.setMinimumIdle(databaseProperties.getMinpool());
        config.setMaximumPoolSize(databaseProperties.getMaxpool());
    }
}

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(MybatisMasterProperties.class)
// EnableConfigurationProperties : MybatisMasterProperties를 설정하게 한다.
class MasterDatabaseConfig extends DatabaseConfig{

    public static final String QUALIFIER = "masterDataSource";

    @Autowired
    MybatisMasterProperties mybatisMasterProperties;

    /**
      * @Lazy 는 빈이 늦게 생성된다.
      * @Primary 중복된 bean이 있으면 @Primary가 선언된 bean이 등록이된다.
      **/
    @Primary
    @Bean(name = QUALIFIER)
    public DataSource dataSource(){
        System.out.println("test : " + mybatisMasterProperties.toString());
        // DB 데이터 설정
        HikariConfig config = new HikariConfig();
        // DB 데이터 셋팅
        configureDataSource(config, mybatisMasterProperties);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    // @Qualifier : 동일한 타입의 빈 객체들 중에서 특정빈을 사용하도록 설정할수 있다.
    public PlatformTransactionManager transactionManager(@Qualifier(QUALIFIER)DataSource masterDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(masterDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}
