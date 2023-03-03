package database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class ListaZitarica {
    
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DbConfig.url)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM dbo.Zitarice";

            ResultSet result = statement.executeQuery(sql);
            int rowCounter = 0;
            while (result.next()) {
                int id = result.getInt(1); // get by index
                String ime = result.getString("Ime"); // get by column name
                String adresa = result.getString("Adresa"); // get by column name
                String output = "Result form row %d -> Id: %d, Adresa:%s";
                System.out.println(String.format(output, ++rowCounter, id, adresa));
            }
            /* 
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConfig.url);
            List<zitarice> sveZitarice = jdbcTemplate.query(sql, new BrandRowMapper());

            for (zitarice zitarica : sveZitarice) {
                System.out.println(zitarica);
            }
            */
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
