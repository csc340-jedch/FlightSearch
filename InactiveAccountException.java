package accounts;

//Signifies an account does not exist or is marked INACTIVE.
public class InactiveAccountException extends Exception{
    public InactiveAccountException(String message){
        super(message);
    }
}
