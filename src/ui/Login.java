package ui;

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
        if (rememberMeCheckBox.isSelected()) {
            ConfigManager.writeConfig(usernameTextField.getText(), passwordField.getText());
        }

        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }
}
