package com.yhc.factorybean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 实例工厂
/*
    ConnectionFactory cf = new ConnectionFactory();
    cf.getConnection();
 */
public class ConnectionFactory {

    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yhc?useSSL=false", "root", "476004");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
