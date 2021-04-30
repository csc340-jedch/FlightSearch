package ui;

import db.ConnectToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public ComboBox<String> genderComboBox;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String zipCode;
    private String salt;
    private String gender;
    private String status;


    @FXML
    protected void initialize() {
        // Populate gender combobox items
        String[] genders = { "Male", "Female", "Other" };
        genderComboBox.getItems().addAll(genders);

        // Get user info
        username = Controller.getUsername();
        password = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_PASSWORD);
        email = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_EMAIL);
        phoneNumber = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_PHONE);
        firstName = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_FNAME);
        lastName = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_LNAME);
        birthDate = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_BIRTH);
        zipCode = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_ZIP);
        salt = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_SALT);
        gender = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_GENDER);
        status = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_STATUS);


        usernameTextField.setText(username);
        passwordField.setText(password);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        zipCodeTextField.setText(zipCode);
        genderComboBox.setValue(gender);

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

        if (!gender.equals(genderComboBox.getValue())) {
            gender = genderComboBox.getValue();
            clientUpdate("clients","gender", gender, "username", username);
        }

        if (!gender.equals(zipCodeTextField.getText())) {
            zipCode = zipCodeTextField.getText();
            clientUpdate("clients","zipCode", zipCode, "username", username);
        }

        Controller.showMessage("Your settings have been updated and saved!", "Settings saved");
    }
}
