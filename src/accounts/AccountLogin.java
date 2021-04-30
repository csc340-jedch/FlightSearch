package accounts;

import db.ConnectToDB;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
       // return true;


        // Check if the username exists
        if(usernameExists(username)){
            // Get the real password from the database

            //Commented out the encryption until we figure out the hiccup
            String correctPassword = getPassword(username);
            System.out.println("correctPassword:" +correctPassword);

            // Get the account salt to encrypt the password given, so we can compare
            String accountSalt = getSalt(username);
            System.out.println("accountSalt:" + accountSalt);
            System.out.println(accountSalt);
            password = Encryption.getEncryptedPassword(password, accountSalt);
            System.out.println("password:" + password);
            return password.equals(correctPassword);
        }
        return false;
    }

    public static void createAccount(String username, String password, String email, String phoneNumber, String firstName, String lastName, String birthDate, String gender, String zipCode) {
        // Generate salt for encryption

        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);

        // Create a row in the database for the client
        String status = "1";
        String[] values = { username, password, email, phoneNumber, firstName, lastName, birthDate, zipCode, gender, salt, status};
        String query = ConnectToDB.constructInsertQueryString(ConnectToDB.TBL_CLIENTS, values);
        System.out.println(query);
        ConnectToDB.insertUpdateDataCon(query);
    }

    public static boolean usernameExists(String username) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username);

        // rows will be greater than 0 if the username exists.
        return rows > 0;
    }

    public static boolean emailExists(String email) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_EMAIL, email);

        // rows will be greater than 0 if the email exists.
        return rows > 0;
    }

    public static String getPassword(String username) {
        // Get and return the password
        return ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_PASSWORD);
    }

    private static String getSalt(String username) {
        return ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_SALT);
    }
}
