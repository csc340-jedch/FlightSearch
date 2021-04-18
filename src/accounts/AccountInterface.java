package accounts;

public interface AccountInterface {

    public User login() throws InvalidPasswordException, InactiveAccountException;

    public void deleteAccount() throws InactiveAccountException, InvalidPasswordException;

    public User searchAccount() throws InactiveAccountException;

}
