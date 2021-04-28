package accounts;

import db.ConnectToDB;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        // Check if the username exists
        if(usernameExists(username)){
            // Get the real password from the database

            //Commented out the encryption until we figure out the hiccup
            /*String correctPassword = getPassword(username);
            System.out.println(correctPassword);

            // Get the account salt to encrypt the password given, so we can compare
            String accountSalt = selectDataCon("SELECT salt FROM clients WHERE username='" + username + "'");
            System.out.println(accountSalt);
            password = Encryption.getEncryptedPassword(password, accountSalt);*/

            //Code under here will work until we get encryption back up
            String correctPassword = getPassword(username);
            System.out.println(correctPassword);
            System.out.println(password);
            return password.equals(correctPassword);
        }
        return false;
    }

    public static void createAccount(String username, String password, String email, String phoneNumber, String firstName, String lastName, String birthDate) throws NoSuchAlgorithmException {
        // Generate salt for encryption
        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);

        // Create a row in the database for the client
        String form = "', '";
        String clientData = "('"+username+form+encryptedPassword+form+email+form+phoneNumber+form+firstName+form+lastName+form+birthDate+form+salt+"')";
        String query = "INSERT INTO clients VALUES "+clientData;
        ConnectToDB.insertUpdateDataCon(query);
    }

    public static boolean usernameExists(String username) {
        String retName = null;
        String query = "SELECT username FROM clients WHERE username = '"+username+"'";
        ResultSet result;
        Statement stmt;
        System.out.println(query);

        try (Connection con = ConnectToDB.testConnect()) {
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

    public static boolean emailExists(String email) {
        String retEmail = null;
        String query = "SELECT email FROM clients WHERE email = '"+email+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = ConnectToDB.testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retEmail = result.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email.equals(retEmail);
    }

    public static String getPassword(String username) {
        String retPass = null;
        String query = "SELECT password FROM clients WHERE username = '"+username+"'";
        ResultSet result;
        Statement stmt;

        try (Connection con = ConnectToDB.testConnect()) {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()) {
                retPass = result.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retPass;

    }


}
