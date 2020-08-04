package com.yhc.mybatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@PropertySource("mybatis.properties")
public class MyBatisProperties {

    @Value("${mybatis.driverClass}")
    private String driverClass;
    @Value("${mybatis.url}")
    private String url;
    @Value("${mybatis.username}")
    private String username;
    @Value("${mybatis.password}")
    private String password;
    @Value("${mybatis.typeAliasesPackages}")
    private String typeAliasesPackages;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;


    @Value("#{'${list}'.split(',')}")
    private List<String> list;
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }



    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
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

    public String getTypeAliasesPackages() {
        return typeAliasesPackages;
    }

    public void setTypeAliasesPackages(String typeAliasesPackages) {
        this.typeAliasesPackages = typeAliasesPackages;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
}
