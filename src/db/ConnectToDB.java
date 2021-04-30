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

    // List of all database table names
    public static final String TBL_CLIENTS = "clients";
    public static final String TBL_FLIGHTS = "flight_table";

    // TBL_CLIENTS column names
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_FNAME = "first_name";
    public static final String COL_LNAME = "last_name";
    public static final String COL_BIRTH = "birth_date";
    public static final String COL_ZIP = "zipCode";
    public static final String COL_GENDER = "gender";
    public static final String COL_SALT = "salt";
    public static final String COL_STATUS = "status";

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

    public static void insertUpdateDataCon(String _query) {
        //This performs INSERT, UPDATE, and DELETE actions
        System.out.println(_query);
        Statement stmt;
        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            stmt.executeUpdate(_query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNumberOfRows(String _table, String _title, String _value) {
        //This will get the number of rows for a given value
        int retRows = 0;
        Statement stmt;
        ResultSet result;
        String query = "SELECT COUNT(*) FROM " + _table + " WHERE " + _title + " = '" + _value + "'";

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

    public static String getDatabaseValue(String _table, String _knownTitle,String _knownValue,String _unknownTitle) {
        //This will return the value from the database that is being searched for
        String retVal = null;
        Statement stmt;
        ResultSet result;
        String query = "SELECT "+ _unknownTitle+" FROM " + _table + " WHERE " + _knownTitle + " = '" + _knownValue+ "'";

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                retVal = result.getString(_unknownTitle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public static String constructInsertQueryString(String _table, String[] _values) {
        //This will construct the string for insertions
        String clientData = String.join(ConnectToDB.DELIMINATOR, _values);
        if(_table.equals("flight_table")){
            return "INSERT INTO " + _table +" VALUES (DEFAULT, '" + clientData + "')";
        }else
        return "INSERT INTO " + _table +" VALUES (DEFAULT, '" + clientData + "')";
    }

    public static void clientUpdate(String _table, String _changeTitle, String _newValue, String _knownTitle, String _knownValue){
        //This will update information in the clients table
        String query = "UPDATE " + _table + " SET " + _changeTitle + " = '" + _newValue + "' WHERE " + _knownTitle + " = '"+ _knownValue +"'";
        insertUpdateDataCon(query);
    }
}
