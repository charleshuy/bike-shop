/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author patho
 */
public class DBUtil {
    public final static String DB_NAME = "ProjectManager";
    public final static String DB_USERNAME = "sa";
    public final static String DB_PASSWORD = "12345";
    public static Connection getConnection()
            throws SQLException,ClassNotFoundException {
        Connection con = null;
        //to do
        //1. Call Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //HOC THUOC !!!
        //2. Create URL
        String url = "jdbc:sqlserver://localhost:1433;databaseName="+DB_NAME;
        //3. Get connection
        con = DriverManager.getConnection(url, DB_USERNAME,DB_PASSWORD);
        return con;
    }
}
