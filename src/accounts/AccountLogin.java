package accounts;

public class AccountLogin {
    public static boolean credentialsAreValid(String username, String password) {
        String user = db.QuereyFunk.getUsernameFromPassword(password);
        String pass = db.QuereyFunk.getPasswordFromUsername(username);
        return user.equals(pass);
    }

    public static void createAccount(String username, String password, String email) {
        db.QuereyFunk.testClientInsert(username, password, email);
    }

    public static boolean usernameExists(String username) {
        return db.QuereyFunk.usernameExists(username);
    }

    public static boolean emailExists(String email) {
        return db.QuereyFunk.emailExists(email);
    }
}
