package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.ConnectToDB.testConnect;

public class QuereyFunk {

    String clientID;
    String clientFirstName;
    String clientLastName;
    String clientEmail;
    String clientPhone;
    String clientBirthDate;
    String clientGender;
    String clientOrigin;
    String clientUsername;
    String clientPassword;
    String clientStatus;


    protected String getClientID() {
        return clientID;
    }
    protected void setClientID(String clientID) {
        this.clientID = clientID;
    }
    protected String getClientFirstName() {
        return clientFirstName;
    }
    protected void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }
    protected String getClientLastName(){
        return clientLastName;
    }
    protected void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }
    protected String getClientEmail(){
        return clientEmail;
    }
    protected void setClientEmail(String clientEmail){
        this.clientEmail = clientEmail;
    }
    protected String getClientPhone() {
        return clientPhone;
    }
    protected void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
    protected String getClientBirthDate() {
        return clientBirthDate;
    }
    protected void setClientBirthDate(String clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
    }
    protected String getClientGender(){
        return clientGender;
    }
    protected void setClientGender(String clientGender){
        this.clientGender = clientGender;
    }
    protected String getClientOrigin() {
        return clientOrigin;
    }
    protected void setClientOrigin(String clientOrigin) {
        this.clientOrigin = clientOrigin;
    }
    protected String getClientUsername() {
        return clientUsername;
    }
    protected void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
    protected String getClientPassword() {
        return clientPassword;
    }
    protected void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
    protected String getClientStatus() {
        return clientStatus;
    }
    protected void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }


    public static void uploadDataCon(String query){
        Statement stmt;
        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.print(e);
        }

    }

    protected static String downloadDataCon(String query){
        Statement stmt;
        try (Connection con = testConnect()) {
            if (con != null) {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
            } else {
                System.out.println("Database connection is null");
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return query;
    }

    public String fullClientData(String clientFirstNameName, String clientLastName, String clientEmail,
                                 String clientPhone, String clientBirthDate, String clientGender, String clientOrigin,
                                 String clientUsername, String clientPassword, String clientStatus){
        String form = "', '";
        return this.clientFirstName+form+ this.clientLastName+form+ this.clientEmail+form
                + this.clientPhone+form + this.clientBirthDate+form+ this.clientGender+form+ this.clientOrigin+form+ this.clientUsername
                +form+ this.clientPassword+form+ this.clientStatus+"')";
    }
    public static void testClientInsert( String username, String password, String email){
        String form = "', '";
        String clientData = "('"+username+form+password+form+email+"')";
        String query = "INSERT INTO test_client VALUES "+clientData;
        uploadDataCon(query);
    }
    public String insertClient(){
        return "INSERT INTO clients VALUES (DEFAULT" + fullClientData(clientFirstName, clientLastName,
                clientEmail, clientPhone, clientBirthDate,clientGender, clientOrigin, clientUsername, clientPassword,
                clientStatus);
    }
    public static void insertClientData(String query){
        uploadDataCon(query);
    }
    public static String getClientPasswordFromName(String clientFirstName, String clientLastName){
        String query = "SELECT client_password FROM clients WHERE first_name = '"+clientFirstName+"' && last_name = '"
                +clientLastName+"'";
        return downloadDataCon(query);
    }
    public static String getPasswordFromUsername(String clientUsername){
        String query = "SELECT client_password FROM clients WHERE client_username = '"+clientUsername+"'";
        return downloadDataCon(query);
    }
    public static String getUsernameFromPassword(String clientPassword){
        String query = "SELECT client_username FROM clients WHERE client_password = '"+clientPassword+"'";
        return downloadDataCon(query);
    }
    public static String getClientPasswordFromID(String clientID){
        String query = "SELECT client_password FROM clients WHERE client_id = "+clientID;
        return downloadDataCon(query);
    }

    public static String getClientNameFromPassword(String password){
        String query1 = "SELECT first_name FROM clients WHERE client_password = '"+password+"'";
        String query2 = "SELECT last_name FROM clients WHERE client_password = '"+password+"'";
        String first = downloadDataCon(query1);
        String last = downloadDataCon(query2);

        return first+" "+last;
    }

    public static boolean usernameExists(String username) {
        String retName = null;
        String query = "SELECT client_username FROM clients WHERE client_username = '"+username+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retName = result.getString("client_username");
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return username.equals(retName);

    }

    public static boolean emailExists(String email) {
        String retEmail = null;
        String query = "SELECT client_email FROM clients WHERE client_email = '"+email+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retEmail = result.getString("client_email");
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return email.equals(retEmail);

    }
}
