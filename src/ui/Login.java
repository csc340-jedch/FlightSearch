package ui;

import accounts.AccountLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Login {
    public Button loginButton;
    public CheckBox rememberMeCheckBox;
    public TextField usernameTextField;
    public PasswordField passwordField;
    public Button registerButton;

    public Login() {
        System.out.println("Started Login");
    }

    @FXML
    protected void initialize() {
        String username = ConfigManager.readSetting("username");
        String password =ConfigManager.readSetting("password");

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            usernameTextField.setText(username);
            passwordField.setText(password);
            rememberMeCheckBox.setSelected(true);
        }
    }

    public void loginButtonClick(ActionEvent actionEvent) throws IOException {
        login();
    }

    public void passwordKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    private void login() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (AccountLogin.credentialsAreValid(username, password)) {
            // Username and password is correct

            if (rememberMeCheckBox.isSelected()) {
                ConfigManager.writeConfig(username, password);
            }

            Controller controller = Controller.getInstance();
            controller.changePane("menu");
        } else {
            // Username and password is incorrect
            Controller.showMessage("Incorrect login credentials provided.", "Login Failed");
        }
    }

    public void registerButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("register");
    }
}
