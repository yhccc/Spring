package com.yhc.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@ComponentScan(basePackages = "com.yhc.mybatis")
@MapperScan(basePackages = "com.yhc.mybatis")
public class MyBatisAppConfig {

    @Autowired
    private MyBatisProperties myBatisProperties;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        // set注入
        dataSource.setDriverClassName(myBatisProperties.getDriverClass());
        dataSource.setUrl(myBatisProperties.getUrl());
        dataSource.setUsername(myBatisProperties.getUsername());
        dataSource.setPassword(myBatisProperties.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // set注入
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(myBatisProperties.getTypeAliasesPackages());

        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(myBatisProperties.getMapperLocations());
            sqlSessionFactoryBean.setMapperLocations(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("UserDAOMapper.xml"));
        return sqlSessionFactoryBean;
    }
}
