package com.example;

import com.azure.identity.DefaultAzureCredentialBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AzureMySQLDemo {
    public static void main(String[] args) {
        String user = "democlient@0c53c388-7eb5-482b-93e5-b18fe1e53cf7";
        String accessToken = new DefaultAzureCredentialBuilder().build()
            .getToken(new com.azure.core.credential.TokenRequestContext()
                .addScopes("https://ossrdbms-aad.database.windows.net/.default"))
            .block()
            .getToken();

        String jdbcUrl = String.format("jdbc:mysql://poctesting.mysql.database.azure.com:3306/userdb?sslMode=REQUIRED");

        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, accessToken)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOW()");
            while (rs.next()) {
                System.out.println("Current Time: " + rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
