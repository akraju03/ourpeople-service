package com.example.helloworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class Test {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/test";
        String driver = "com.mysql.jdbc.Driver";
        String usr = "root";
        String pwd = "root";
        try {
            //Loading the Driver using DbUtils static method
            DbUtils.loadDriver(driver);
            conn = DriverManager.getConnection(url, usr, pwd);
            
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            //Closing the connection quietly, means it will handles the SQLException
            DbUtils.closeQuietly(conn);
        }
    }
}
