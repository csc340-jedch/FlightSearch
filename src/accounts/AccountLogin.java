package accounts;

import java.security.NoSuchAlgorithmException;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        // TODO: Implement this using database connection
        return true;
    }

    public static void createAccount(String username, String password, String email, String phoneNumber, String firstName, String lastName, String birthDate) throws NoSuchAlgorithmException {
        // Generate salt for encryption
        String salt = Encryption.getRandomSalt();
        System.out.println("Salt: " + salt);

        String encryptedPassword = Encryption.getEncryptedPassword(password, salt);
        System.out.println("Encrypted password: " + encryptedPassword);


        // TODO: Implement this using database connection
    }

    public static boolean usernameExists(String username) {
        // TODO: Implement this using database connection
        return false;
    }

    public static boolean emailExists(String email) {
        // TODO: Implement this using database connection
        return false;
    }
}
