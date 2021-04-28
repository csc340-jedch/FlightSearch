package accounts;

import db.ConnectToDB;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        // Check if the username exists
        if(usernameExists(username)){
            // Get the real password from the database

            //Commented out the encryption until we figure out the hiccup
            String correctPassword = getPassword(username);
            System.out.println(correctPassword);

            // Get the account salt to encrypt the password given, so we can compare
            String accountSalt = getSalt(username);
            System.out.println(accountSalt);
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
        String[] values = { username, encryptedPassword, email, phoneNumber, firstName, lastName, birthDate, salt };
        String query = ConnectToDB.constructInsertQueryString("clients", values);
        ConnectToDB.insertUpdateDataCon(query);
    }

    public static boolean usernameExists(String username) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows("clients", "username", username);

        // rows will be greater than 0 if the username exists.
        return rows > 0;
    }

    public static boolean emailExists(String email) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows("clients", "email", email);

        // rows will be greater than 0 if the email exists.
        return rows > 0;
    }

    public static String getPassword(String username) {
        // Get and return the password
        return ConnectToDB.getDatabaseValue("clients", "username", username, "password");
    }

    private static String getSalt(String username) {
        return ConnectToDB.getDatabaseValue("clients", "username", username, "salt");
    }


}
