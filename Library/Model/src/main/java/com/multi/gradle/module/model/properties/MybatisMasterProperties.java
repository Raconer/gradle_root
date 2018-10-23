package com.multi.gradle.module.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dhokim on  2018-10-22
 */
@ConfigurationProperties(prefix = MybatisMasterProperties.PREFIX)
// implements IMybatisProperties를 한 이유 : 인터페이스 추가
public class MybatisMasterProperties implements IMybatisProperties{
    public static final String PREFIX = "jdbc.default";

    private String driver;
    private String url;
    private String username;
    private String password;
    private Integer maxpool;
    private Integer minpool;

    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxpool() {
        return maxpool;
    }
    public void setMaxpool(Integer maxpool) {
        this.maxpool = maxpool;
    }

    public Integer getMinpool() {
        return minpool;
    }
    public void setMinpool(Integer minpool) {
        this.minpool = minpool;
    }

    @Override
    public String toString() {
        return "MybatisMasterProperties{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxpool=" + maxpool +
                ", minpool=" + minpool +
                '}';
    }
}
