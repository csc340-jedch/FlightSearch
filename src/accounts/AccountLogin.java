package accounts;

import db.ConnectToDB;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        // Check if the username exists
        if(usernameExists(username)){
            // Get the real password from the database
            String correctPassword = getPassword(username);

            // Get the account salt to encrypt the password given, so we can compare
            String accountSalt = ConnectToDB.selectDataCon("SELECT salt FROM clients WHERE username='" + username + "");
            password = Encryption.getEncryptedPassword(password, accountSalt);
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
        //db.QuereyFunk.testClientInsert(username, encryptedPassword, email);
    }

    public static boolean usernameExists(String username) {
        //return db.QuereyFunk.usernameExists(username);
        return true;
    }

    public static boolean emailExists(String email) {
        //return db.QuereyFunk.emailExists(email);
        return true;
    }

    public static String getPassword(String username) {
        String query = "SELECT password from clients WHERE username='" + username + "'";
        return ConnectToDB.selectDataCon(query);
    }
}
