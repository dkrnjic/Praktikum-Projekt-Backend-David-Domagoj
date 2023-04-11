package database;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner; // Import the Scanner class

public class autentikacija {
    public static void main(String[] args) {
        int izbor = 0;
        String username = "badmin";
        String password = "password";
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        do {
            System.out.println("1. Test login\n 2. Test New user\n 0. izlaz");
            izbor = myObj.nextInt(); // Read user input
            switch (izbor) {
                case 1:
                    String authenticated = autentikacija.authenticate(username, password);
                    if (authenticated != null) {
                        System.out.println("Autentikacija uspjesna!");
                    } else {
                        System.out.println("Autentikacija neuspjesna!");
                    }
                    break;
                case 2:
                    String newAdminUsername = "newadmin";
                    String newAdminPassword = "newpassword";
                    createAdmin(newAdminUsername, newAdminPassword);
                    // createAdmin(newAdminUsername, newAdminPassword);
                    break;

                default:
                    break;
            }
        } while (izbor != 0);
    }

    public static String authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                PreparedStatement stmt = conn
                        .prepareStatement("select password from dbo.Administrator where Administrator.Username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("Password");
                boolean authenticated = BCrypt.checkpw(password, passwordHash);
                if (authenticated) {
                    // return the URL to which you want to redirect the user
                    return "http://localhost:3000/dashboard";
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            // Log the error and return an error message to the caller
            System.err.println("Error autentikacije usera: " + e.getMessage());
            return null;
        }
    }

    public static void createAdmin(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                PreparedStatement stmt = conn
                        .prepareStatement("INSERT INTO dbo.Administrator (Username, Password) VALUES (?, ?)");) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Log the error and return an error message to the caller
            System.err.println("Error kreiranja admina: " + e.getMessage());
        }
    }
}
