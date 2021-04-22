package accounts;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        String user = db.QuereyFunk.getUsernameFromPassword(password);
        String pass = db.QuereyFunk.getPasswordFromUsername(username);
        return user.equals(pass);
    }

    public static void createAccount(String username, String password, String email) {
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
