package ui;

import db.ConnectToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static db.ConnectToDB.clientUpdate;

public class AccountSettings {
    public Button BackButton;
    public TextField usernameTextField;
    public PasswordField passwordField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField zipCodeTextField;
    public DatePicker birthDatePicker;
    public Button saveChangesButton;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String birthDate;

    @FXML
    protected void initialize() {
        // Get user info
        username = Controller.getUsername();
        password = ConnectToDB.getDatabaseValue("clients", "username", username, "password");
        email = ConnectToDB.getDatabaseValue("clients", "username", username, "email");
        phoneNumber = ConnectToDB.getDatabaseValue("clients", "username", username, "phone");
        firstName = ConnectToDB.getDatabaseValue("clients", "username", username, "first_name");
        lastName = ConnectToDB.getDatabaseValue("clients", "username", username, "last_name");
        //zipCode = ConnectToDB.getDatabaseValue("clients", "username", username, "zipCode");
        birthDate = ConnectToDB.getDatabaseValue("clients", "username", username, "birth_date");

        usernameTextField.setText(username);
        passwordField.setText(password);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        //zipCodeTextField.setText(zipCode);

        //LocalDateTime date =
        //birthDatePicker.setValue();
    }

    public void BackButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void saveChangesButtonClick(ActionEvent actionEvent) {
        // Check if username was changed
        String origUsername = username;
        if (!username.equals(usernameTextField.getText())) {
            username = usernameTextField.getText();
            clientUpdate("clients","username", username, "username", origUsername);
        }

        // Check if password was changed
        if (!password.equals(passwordField.getText())) {
            password = passwordField.getText();
            clientUpdate("clients","password",password, "username", username);
        }

        // Check if email was changed
        if (!email.equals(emailTextField.getText())) {
            email = emailTextField.getText();
            clientUpdate("clients","email", email, "username", username);
        }

        // Check if phone number was changed
        if (!phoneNumber.equals(phoneNumberTextField.getText())) {
            phoneNumber = phoneNumberTextField.getText();
            clientUpdate("clients","phone", phoneNumber, "username", username);
        }

        // Check if first name was changed
        if (!firstName.equals(firstNameTextField.getText())) {
            firstName = firstNameTextField.getText();
            clientUpdate("clients","first_name", firstName, "username", username);
        }

        // Check if last name was changed
        if (!lastName.equals(lastNameTextField.getText())) {
            lastName = lastNameTextField.getText();
            clientUpdate("clients","last_name", lastName, "username", username);
        }
        Controller.showMessage("Your settings have been updated and saved!", "Settings saved");
    }
}
