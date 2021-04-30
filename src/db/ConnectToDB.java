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
        //This will get the number of rows for a given value
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
        //This will return the value from the database that is being searched for
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

    public static String constructInsertQueryString(String table, String[] values) {
        //This will construct the string for insertions
        String clientData = String.join(ConnectToDB.DELIMINATOR, values);
        if(table.equals("flight_table")){
            return "INSERT INTO " + table +" VALUES (DEFAULT, '" + clientData + "')";
        }else
        return "INSERT INTO " + table +" VALUES ('" + clientData + "')";
    }

    public static void clientUpdate(String table, String changeTitle, String newValue, String knownTitle, String knownValue){
        //This will update information in the clients table
        String query = "UPDATE " + table + " SET " + changeTitle + " = '" + newValue + "' WHERE " + knownTitle + " = '"+ knownValue +"'";
        insertUpdateDataCon(query);
    }
}




