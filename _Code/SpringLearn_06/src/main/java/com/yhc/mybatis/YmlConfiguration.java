package com.yhc.mybatis;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;


@Configuration
public class YmlConfiguration {


    @Bean
    public PropertySourcesPlaceholderConfigurer configurer(){
        // 读取yml并转换成Properties
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("mybatis.yml"));
        Properties properties = yamlPropertiesFactoryBean.getObject();

        // 创建PropertySourcesPlaceholderConfigurer
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setProperties(properties);
        return configurer;
    }

}
