package accounts;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        String user = db.QuereyFunk.getUsernameFromPassword(password);
        String pass = db.QuereyFunk.getPasswordFromUsername(username);
        return user.equals(pass);
    }

    public static void createAccount(String username, String password, String email, String phoneNumber, String firstName, String lastName, String birthDate) throws NoSuchAlgorithmException {
        // Generate salt for encryption
        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);

        db.QuereyFunk.testClientInsert(username, encryptedPassword, email);
    }

    public static boolean usernameExists(String username) {
        return db.QuereyFunk.usernameExists(username);
    }

    public static boolean emailExists(String email) {
        return db.QuereyFunk.emailExists(email);
    }
}
