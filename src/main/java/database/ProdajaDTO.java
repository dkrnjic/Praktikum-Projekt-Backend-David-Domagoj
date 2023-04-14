package database;

import java.sql.*;

public class ProdajaDTO {
    private int id = 0;
    private int Zid = 0;
    private String ime;
    private String tipZitarice;
    private int kolicina;
    private float cijena;
    String[] parts;
    String firstName = "";
    String lastName = "";

    public ProdajaDTO() {
    }

    public ProdajaDTO(String ime, String tip, int kolicina, float cijena) {
        this.ime = ime;
        this.tipZitarice = tip;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }

    public int getKlijentId() {
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                CallableStatement stmt = conn.prepareCall("{call GetClientIdByFullName(?, ?)}")) {

            parts = ime.split(" ");
            firstName = parts[0];
            lastName = parts[1];
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                id = rs.getInt("KlijentID");
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        return id;
    }

    public int getZitaricaId() {
        try (Connection conn = DriverManager.getConnection(DbConfig.url);
                CallableStatement stmt = conn.prepareCall("{call GetCropsIdByFullName(?)}")) {

            stmt.setString(1, tipZitarice);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                Zid = rs.getInt("ZitariceID");
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        return Zid;
    }

    public String gettipZitarice() {
        return tipZitarice;
    }

    public String getIme() {
        return ime;
    }

    public int getKolicina() {
        return kolicina;
    }

    public float getCijena() {
        return cijena;
    }

}
