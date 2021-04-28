package accounts;

//Signifies the inputted password was incorrect.
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message){
        super(message);
    }
}
