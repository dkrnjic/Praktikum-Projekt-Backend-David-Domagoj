package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import database.DbConfig;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class AuthenticationController {

    @CrossOrigin(origins = "http://127.0.0.1:5173/")
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        boolean authenticated = authenticateUser(username, password);

        Map<String, Object> response = new HashMap<>();
        if (authenticated) {
            response.put("success", true);
            System.out.println("uspih");
            response.put("message", "Authentication successful");
        } else {
            response.put("success", false);
            System.out.println("neuspih");
            response.put("message", "Invalid username or password");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean authenticateUser(String username, String password) {
        boolean authenticated = false;
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                PreparedStatement stmt = conn
                        .prepareStatement("select password from dbo.Administrator where Administrator.Username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("Password");
                authenticated = BCrypt.checkpw(password, passwordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authenticated;
    }
}
