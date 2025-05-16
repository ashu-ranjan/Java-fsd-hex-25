package com.lms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
    private  String url = "jdbc:mysql://localhost:3306/lms_db";
    private  String userDB = "root";
    private  String passDB = "**************";
    private  String driver = "com.mysql.cj.jdbc.Driver";
    private  Connection conn;

    public  Connection connect() {
        // Step 1: load the driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // Step 2: Establish the connection
        try {
            conn = DriverManager.getConnection(url, userDB, passDB);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void close(){
        try {
            if (!conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
