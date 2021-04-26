package accounts;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        if((db.QuereyFunk.usernameExists(username))){
            System.out.println("2\n");
            String pass = db.QuereyFunk.getPasswordFromUsernameTest(username);
            System.out.println(pass+"\n");
            if(password.equals(pass)){
                System.out.println("3\n");
            }
            return password.equals(pass);
        }else
            return false;
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
