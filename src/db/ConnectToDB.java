package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "spass";
    private static final String CONN = "jdbc:mysql://35.237.96.145/jedch_db";


    public static void testConnect() throws SQLException {

        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
            System.out.println("Connected");

            stmt = con.createStatement();
            stmt.executeUpdate(CreateTables.CREATE_CLIENT_TABLE);


        } catch (SQLException e) {
            System.err.print(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}


