package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {
    //Creates the connection request
    private static final String USERNAME = "root";
    private static final String PASSWORD = "spass";
    private static final String CONN = "jdbc:mysql://35.237.96.145/jedch_db";

    public static Connection testConnect() throws SQLException {
        //Attempts the connection
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.print("Did not connect to database: " + e);
        }
        return con;
    }

    public static String selectDataCon(String query) {
        //This performs SELECT requests that return results
        Statement stmt;
        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    public static String insertUpdateDataCon(String query) {
        //This performs INSERT, UPDATE, and DELETE actions
        Statement stmt;
        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}


