package database;

import java.sql.*;

public class SqlConnection{   

public static void main(String[] args) {
    // jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
    String databaseUrl = "jdbc:sqlserver://localhost:1433;user=rpuser;encrypt=false;password=1234";
    Connection conn = null;
    try {
        conn = DriverManager.getConnection(databaseUrl); 
        if (conn != null)
            System.out.println("Connected");
            
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("Driver: " + meta.getDriverName() + " " + meta.getDriverVersion());
        System.out.println("Connection: " + meta.getConnection());
        System.out.println("Product version: " + meta.getDatabaseProductVersion());
        String sql = "Select @@servername as ServerName";

       PreparedStatement statement = conn.prepareStatement(sql);
       ResultSet rs = statement.executeQuery();

       while (rs.next())
           System.out.println("Server name: " + rs.getString("ServerName"));
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        try {
            if (conn != null && conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

}
