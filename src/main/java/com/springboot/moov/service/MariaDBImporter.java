package com.springboot.moov.service;

import com.springboot.moov.config.MariaDBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MariaDBImporter {
    public static void importDataToMariaDB(String filePath) {
        String insertSQL = "INSERT INTO my_table (data) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(MariaDBConfig.JDBC_URL, MariaDBConfig.USER, MariaDBConfig.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL);
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                pstmt.setString(1, line);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

            System.out.println("Data imported to MariaDB successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}