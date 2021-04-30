package accounts;

public interface AccountInterface {

    User login() throws InvalidPasswordException, InactiveAccountException;

    void deleteAccount() throws InactiveAccountException, InvalidPasswordException;

    private User searchAccount() throws InactiveAccountException, InvalidPasswordException {
        return null;
    }

}
