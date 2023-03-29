package database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import java.sql.Timestamp;
import java.time.Instant;

public class DodajOtkup {

    public static void main(String[] args) {
        int otkupKorisnikId = 2;
        int zitaricaId = 1;
        float kolicina = 550;
        Timestamp time = Timestamp.from(Instant.now());
        float primjesa = 2.1f;
        float vlaga = 5.2f;
        float cijena = 124f;
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                CallableStatement stmt = conn.prepareCall("{call DodajOtkup(?, ?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, otkupKorisnikId);
            stmt.setInt(2, zitaricaId);
            stmt.setFloat(3, kolicina);
            stmt.setTimestamp(4, new Timestamp(time.getTime()));
            stmt.setFloat(5, primjesa);
            stmt.setFloat(6, vlaga);
            stmt.setFloat(7, cijena);

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
