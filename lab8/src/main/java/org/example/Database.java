package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";
    private static BasicDataSource d = null;


    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        if(d == null) {
            createConnection();
        }
        return d.getConnection();
    }

    private static void createConnection() {

//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            connection.setAutoCommit(false);
            d = new BasicDataSource();
            d.setUrl(URL);
            d.setUsername(USER);
            d.setPassword(PASSWORD);
            d.setDefaultAutoCommit(false);
            d.setDriverClassName("oracle.jdbc.OracleDriver");
//
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
    }

//    public static void closeConnection() {
//        //TODO
//        try {
//            connection.close();
//            connection = null;
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//    }
}