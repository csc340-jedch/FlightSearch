package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.ConnectToDB.testConnect;

public class QuereyFunk {

    //Probably don't need this stuff

    /*String clientID;
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

    protected String getClientLastName() {
        return clientLastName;
    }

    protected void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    protected String getClientEmail() {
        return clientEmail;
    }

    protected void setClientEmail(String clientEmail) {
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

    protected String getClientGender() {
        return clientGender;
    }

    protected void setClientGender(String clientGender) {
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


    public static String selectDataCon(String query) {
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
        Statement stmt;
        try (Connection con = testConnect()) {
            if (con != null) {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
            } else {
                System.out.println("Database connection is null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}

    public String fullClientData(String clientFirstNameName, String clientLastName, String clientEmail,
                                 String clientPhone, String clientBirthDate, String clientGender, String clientOrigin,
                                 String clientUsername, String clientPassword, String clientStatus){
        String form = "', '";
        return this.clientFirstName+form+ this.clientLastName+form+ this.clientEmail+form
                + this.clientPhone+form + this.clientBirthDate+form+ this.clientGender+form+ this.clientOrigin+form+ this.clientUsername
                +form+ this.clientPassword+form+ this.clientStatus+"')";
    }
    public static void testClientInsert( String username, String password, String email, String phoneNumber, String firstName,
                                         String lastName, String birthDate){
        String form = "', '";
        String clientData = "('"+username+form+password+form+email+form+phoneNumber+form+firstName+form+lastName+form+birthDate+"')";
        String query = "INSERT INTO clients VALUES "+clientData;
        insertUpdateDataCon(query);
    }
    public String insertClient(){
        return "INSERT INTO clients VALUES (DEFAULT" + fullClientData(clientFirstName, clientLastName,
                clientEmail, clientPhone, clientBirthDate,clientGender, clientOrigin, clientUsername, clientPassword,
                clientStatus);
    }
    public static void insertClientData(String query){
        insertUpdateDataCon(query);
    }
    public static String getClientPasswordFromName(String clientFirstName, String clientLastName){
        String query = "SELECT client_password FROM clients WHERE first_name = '"+clientFirstName+"' && last_name = '"
                +clientLastName+"'";

        return selectDataCon(query);
    }

    public static String getClientPasswordFromID(String clientID){
        String query = "SELECT client_password FROM clients WHERE client_id = "+clientID;
        return selectDataCon(query);
    }

    public static String getClientNameFromPassword(String password){
        String query1 = "SELECT first_name FROM clients WHERE client_password = '"+password+"'";
        String query2 = "SELECT last_name FROM clients WHERE client_password = '"+password+"'";
        String first = selectDataCon(query1);
        String last = selectDataCon(query2);

        return first+" "+last;
    }

    public static boolean usernameExists(String username) {
        String retName = null;
        String query = "SELECT username FROM test_client WHERE username = '"+username+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retName = result.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(retName);
        if(username.equals(retName)){
            System.out.println("1\n");
            return true;
        }else
            System.out.println("false");
        return false;

    }

   public static String getPasswordFromUsernameTest(String username) {
        String retName = null;
        String query = "SELECT password FROM clients WHERE username = '"+username+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retName = result.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(retName);
        if(retName!= null){
            System.out.println("1\n");
            return retName;


        }else
            System.out.println("no password found");
        return null;

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
            e.printStackTrace();
        }
        return email.equals(retEmail);

    }
}*/
}
