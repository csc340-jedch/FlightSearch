package ui;

import accounts.AccountManager;
import db.ConnectToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

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
    public Button deactivateButton;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String zipCode;
    private String gender;


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
        gender = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_GENDER);

        usernameTextField.setText(username);
        passwordField.setText(password);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        zipCodeTextField.setText(zipCode);
        genderComboBox.setValue(gender);

        LocalDate date = LocalDate.parse(birthDate);
        birthDatePicker.setValue(date);
    }

    public void BackButtonClick(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void saveChangesButtonClick(ActionEvent _actionEvent) {
        // Check if username was changed
        String origUsername = username;
        if (!username.equals(usernameTextField.getText())) {
            username = usernameTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_USERNAME, username, ConnectToDB.COL_USERNAME, origUsername);
        }

        // Check if password was changed
        if (!password.equals(passwordField.getText())) {
            password = passwordField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_PASSWORD,password, ConnectToDB.COL_USERNAME, username);
        }

        // Check if email was changed
        if (!email.equals(emailTextField.getText())) {
            email = emailTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_EMAIL, email, ConnectToDB.COL_USERNAME, username);
        }

        // Check if phone number was changed
        if (!phoneNumber.equals(phoneNumberTextField.getText())) {
            phoneNumber = phoneNumberTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_PHONE, phoneNumber, ConnectToDB.COL_USERNAME, username);
        }

        // Check if first name was changed
        if (!firstName.equals(firstNameTextField.getText())) {
            firstName = firstNameTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_FNAME, firstName, ConnectToDB.COL_USERNAME, username);
        }

        // Check if last name was changed
        if (!lastName.equals(lastNameTextField.getText())) {
            lastName = lastNameTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_LNAME, lastName, ConnectToDB.COL_USERNAME, username);
        }

        if (!gender.equals(genderComboBox.getValue())) {
            gender = genderComboBox.getValue();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_GENDER, gender, ConnectToDB.COL_USERNAME, username);
        }

        if (!zipCode.equals(zipCodeTextField.getText())) {
            zipCode = zipCodeTextField.getText();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_ZIP, zipCode, ConnectToDB.COL_USERNAME, username);
        }

        if (!birthDate.equals(birthDatePicker.getValue().toString())) {
            birthDate = birthDatePicker.getValue().toString();
            clientUpdate(ConnectToDB.TBL_CLIENTS,ConnectToDB.COL_BIRTH, birthDate, ConnectToDB.COL_USERNAME, username);
        }

        Controller.showMessage("Your settings have been updated and saved!", "Settings saved");
    }

    public void deactivateButtonClick(ActionEvent _actionEvent) throws IOException {
        // Send the user a conformation dialog
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Deactivate Account");
        dialog.setHeaderText("Deactivate Account Confirmation Dialog");
        dialog.setContentText("Are you sure you want to deactivate your account? Once done, you will not be able to use this account again.");
        dialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            AccountManager.deactivateAccount(username);

            // Bring back to login screen
            Controller controller = Controller.getInstance();
            controller.changePane("login");
        }
    }
}
