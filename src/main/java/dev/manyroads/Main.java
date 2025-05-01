package dev.manyroads;


import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:E:/temp/testdb.db";

        // to connect your Java applications and JDBC drivers
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        // JDBC interface represents the connection with DBMS
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connection is valid.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
