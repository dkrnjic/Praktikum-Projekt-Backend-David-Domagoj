package database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import javax.sql.DataSource;

public class DohvatiFarmere {

    public static void main(String[] args) {
        String name;
        String address;

        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                PreparedStatement stmt = conn.prepareStatement("EXEC DodajOtkupKorisnika");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                name = rs.getString("Ime");
                name = rs.getString("Adresa");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
