package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {
    //Creates the connection request
    private static final String USERNAME = "root";
    private static final String PASSWORD = "spass";
    private static final String CONN = "jdbc:mysql://35.237.96.145/jedch_db";
    private static final String DELIMINATOR = "', '";

    private static Connection testConnect() throws SQLException {
        //Attempts the connection
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.print("Did not connect to database: " + e);
        }
        return con;
    }

    public static void insertUpdateDataCon(String query) {
        //This performs INSERT, UPDATE, and DELETE actions
        Statement stmt;
        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNumberOfRows(String table, String title, String value) {
        int retRows = 0;
        Statement stmt;
        ResultSet result;
        String query = "SELECT COUNT(*) FROM " + table + " WHERE " + title + " = '" + value + "'";

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                retRows = result.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retRows;

    }

    public static String getDatabaseValue(String table, String knownTitle,String knownValue,String unknownTitle) {
        String retVal = null;
        Statement stmt;
        ResultSet result;
        String query = "SELECT "+unknownTitle+" FROM " + table + " WHERE " + knownTitle + " = '" + knownValue+ "'";

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                retVal = result.getString(unknownTitle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    /*public static String getFlightRowInfo(String username){

    }*/

    public static String constructFlightInsertQueryString(String table, String[] values) {
        String clientData = String.join(ConnectToDB.DELIMINATOR, values);
        return "INSERT INTO test_flights VALUES ('" + clientData + "')";
        //String flightInsert = "INSERT INTO test_flights VALUES ('" + values[0] + "', '" + values[1] + "', '" + values[2] + "', '" + values[3] + "')";
        //System.out.println(flightInsert);
        //return flightInsert;
    }

    public static String constructClientInsertQueryString(String table, String[] values) {
        String clientData = String.join(ConnectToDB.DELIMINATOR, values);
        return "INSERT INTO clients VALUES ('" + clientData + "')";
    }
}




