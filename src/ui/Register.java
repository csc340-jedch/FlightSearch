package ui;

import accounts.AccountManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
    public TextField zipCodeTextField;
    public ComboBox<String> genderComboBox;

    @FXML
    protected void initialize() {
        // Populate genders combobox choices
        String[] genders = {"Male", "Female", "Other"};
        genderComboBox.getItems().addAll(genders);
    }

    public void exitButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("login");
    }

    public void createButtonClick(ActionEvent _actionEvent) throws IOException, NoSuchAlgorithmException {

        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        String zipCode = zipCodeTextField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || zipCode.isEmpty() || genderComboBox.getValue() == null || birthDatePicker.getValue() == null) {
            Controller.showMessage("Please fill out all fields", "Fill out all information fields");
            return;
        }

        String birthDate = birthDatePicker.getValue().toString();
        String gender = genderComboBox.getValue().toString();

        // Check if username already exists
        if (AccountManager.usernameExists(username)) {
            Controller.showMessage("An account with this username already exists.", "Username already taken");
            return;
        }

        // Check if email already exists
        if (AccountManager.emailExists(email)) {
            Controller.showMessage("An account with this email already exists.", "Email already taken");
            return;
        }

        // We are clear to create the account
        AccountManager.createAccount(username, password, email, phoneNumber, firstName, lastName, birthDate, gender, zipCode);

        // Set the username of the new account
        Controller.setUsername(username);

        // Go to the main menu
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }
}
