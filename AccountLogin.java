package accounts;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String _username, String _password) {
        if((db.QuereyFunk.usernameExists(_username))){
            System.out.println("2\n");
            String pass = db.QuereyFunk.getPasswordFromUsernameTest(_username);
            System.out.println(pass+"\n");
            if(_password.equals(pass)){
                System.out.println("3\n");
            }
            return _password.equals(pass);
        }else
            return false;
    }

    public static void createAccount(String _username, String _password, String _email, String _phoneNumber, String _firstName, String _lastName, String _birthDate) throws NoSuchAlgorithmException {
        // Generate salt for encryption
        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(_password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);

        db.QuereyFunk.testClientInsert(_username, encryptedPassword, _email);
    }

    public static boolean usernameExists(String _username) {
        return db.QuereyFunk.usernameExists(_username);
    }

    public static boolean emailExists(String _email) {
        return db.QuereyFunk.emailExists(_email);
    }
}
