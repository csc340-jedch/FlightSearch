package ui;

import accounts.AccountLogin;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Register {

    public Button exitButton;
    public Button createButton;
    public TextField usernameTextField;
    public TextField emailTextField;
    public PasswordField passwordField;
    public TextField phoneNumberTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public DatePicker birthDatePicker;

    public void exitButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("login");
    }

    public void createButtonClick(ActionEvent actionEvent) throws IOException {
        // TODO: Create user using database methods

        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String birthDate = birthDatePicker.getValue().toString();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty()) {
            Controller.showMessage("Please fill out all fields", "Fill out all information fields");
            return;
        }

        // Check if username already exists
        if (AccountLogin.usernameExists(username)) {
            Controller.showMessage("An account with this username already exists.", "Username already taken");
            return;
        }

        // Check if email already exists
        if (AccountLogin.emailExists(email)) {
            Controller.showMessage("An account with this email already exists.", "Email already taken");
            return;
        }

        // We are clear to create the account
        AccountLogin.createAccount(username, password, email);

        // Go to the main menu
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }
}
