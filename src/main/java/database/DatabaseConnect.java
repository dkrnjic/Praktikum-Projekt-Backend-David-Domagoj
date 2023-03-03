package database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.util.List;

public class DatabaseConnect{   
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DbConfig.url)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM dbo.Korisnici";

            ResultSet result = statement.executeQuery(sql);
            int rowCounter = 0;
            while (result.next()) {
                int id = result.getInt(1); // get by index
                String ime = result.getString("Ime"); // get by column name
                String adresa = result.getString("Adresa"); // get by column name
                String output = "Result form row %d -> Id: %d, Adresa:%s";
                System.out.println(String.format(output, ++rowCounter, id, adresa));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
