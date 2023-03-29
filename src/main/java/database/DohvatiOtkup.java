package database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import java.sql.Timestamp;
import java.time.Instant;

public class DohvatiOtkup {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                PreparedStatement stmt = conn.prepareStatement("EXEC DohvatiSveOtkupe");
                ResultSet rs = stmt.executeQuery()) {

            // Output column headers
            System.out.printf("%-5s %-10s %-10s %-10s %-23s  %-9s %-9s %-5s\n", "Id", "Coop Id", "ZitaricaId",
                    "Kolicina",
                    "Vrijeme", "Primjesa", "Vlaga", "Cijena");

            while (rs.next()) {
                int id = rs.getInt("Id");
                int korisniciId = rs.getInt("OtkupKorisnikId");
                int zitariceId = rs.getInt("ZitariceId");
                float kolicina = rs.getFloat("Kolicina");
                Timestamp time = rs.getTimestamp("Time");
                float primjesa = rs.getFloat("Primjesa");
                float vlaga = rs.getFloat("Vlaga");
                float cijena = rs.getFloat("Cijena");

                // Do something with the row data
                System.out.printf("%-5d %-10d %-10d %-10.2f %-23s  %-9.2f %-9.2f %-5s\n", id, korisniciId, zitariceId,
                        kolicina, time, primjesa, vlaga, cijena);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
