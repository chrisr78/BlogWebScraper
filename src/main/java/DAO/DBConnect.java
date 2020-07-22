package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3308/TechBlog";
        String userName = "TechBlog";
        String passWord = "T3chBl0g!";

        try {
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
