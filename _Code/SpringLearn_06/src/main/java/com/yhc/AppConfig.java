package com.yhc;

import com.yhc.bean.ConnectionFactoryBean;
import com.yhc.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    // 简单对象
    @Bean
    public User user(){
        return new User();
    }

    // 复杂对象
    @Bean
    public Connection conn(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/yhc?useSSL=false",
                    "root",
                    "476004");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Bean("connection")
    @Scope("singleton")
    @Lazy
    public Connection conn1(){
        ConnectionFactoryBean connectionFactoryBean = new ConnectionFactoryBean();
        Connection connection = null;
        try {
            connection = connectionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
