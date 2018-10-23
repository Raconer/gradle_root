package com.multi.gradle.module.model.properties;

/**
 * Created by dhokim on  2018-10-22
 */
// DataBase Data Model
public interface IMybatisProperties {
    String getDriver();
    void setDriver(String driver);

    String getUrl();
    void setUrl(String url);

    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);

    Integer getMaxpool();
    void setMaxpool(Integer maxpool);

    Integer getMinpool();
    void setMinpool(Integer minpool);
}
