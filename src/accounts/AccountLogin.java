package accounts;

import db.ConnectToDB;
import ui.Controller;

public class AccountLogin {
    public static boolean credentialsAreValid(String _username, String _password) {

        // Check if the username exists
        if (usernameExists(_username)) {
            // Check the status of the account
            String accountStatus = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, _username, ConnectToDB.COL_STATUS);

            if (!accountStatus.equals("1")) {
                Controller.showMessage("Account has been deactivated.", "Account deactivated");
                return false;
            }

            // Get the real password from the database
            String correctPassword = getPassword(_username);
            System.out.println("correctPassword:" + correctPassword);

            // Get the account salt to encrypt the password given, so we can compare
            String accountSalt = getSalt(_username);
            System.out.println("accountSalt:" + accountSalt);
            _password = Encryption.getEncryptedPassword(_password, accountSalt);
            System.out.println("password:" + _password);
            return _password.equals(correctPassword);
        }
        return false;
    }

    public static void createAccount(String _username, String _password, String _email, String _phoneNumber, String _firstName, String _lastName, String _birthDate, String _gender, String _zipCode) {
        // Generate salt for encryption

        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(_password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);

        // Create a row in the database for the client
        String status = "1";
        String[] values = {_username, encryptedPassword, _email, _phoneNumber, _firstName, _lastName, _birthDate, _zipCode, _gender, salt, status};
        String query = ConnectToDB.constructInsertQueryString(ConnectToDB.TBL_CLIENTS, values);
        System.out.println(query);
        ConnectToDB.insertUpdateDataCon(query);
    }

    public static boolean usernameExists(String _username) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, _username);

        // rows will be greater than 0 if the username exists.
        return rows > 0;
    }

    public static boolean emailExists(String _email) {
        // Get the number of rows where the username exists.
        int rows = ConnectToDB.getNumberOfRows(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_EMAIL, _email);

        // rows will be greater than 0 if the email exists.
        return rows > 0;
    }

    public static String getPassword(String _username) {
        // Get and return the password
        return ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, _username, ConnectToDB.COL_PASSWORD);
    }

    public static void deactivateAccount(String _username) {
        // Sets users status to inactive
        ConnectToDB.clientUpdate(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_STATUS, "-1", ConnectToDB.COL_USERNAME, _username);
    }

    private static String getSalt(String _username) {
        return ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, _username, ConnectToDB.COL_SALT);
    }
}
