package controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.App;
import main.Database;
import main.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class RegistrationController {

    @FXML
    private Button goBackButton = new Button();

    @FXML
    private TextField emailField = new TextField();

    @FXML
    private PasswordField passwordField = new PasswordField();

    @FXML
    private PasswordField secondPasswordField = new PasswordField();

    @FXML
    private Button registerButton = new Button();

    @FXML
    private Label errorLabel = new Label();

    @FXML
    public void register() {
        errorLabel.setText("");

        if (Arrays.equals(passwordField.getText().getBytes(), new byte[]{})) {
            errorLabel.setText("Password field cannot be empty");
            return;
        }

        if (!emailField.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            errorLabel.setText("Wrong type of email");
            return;
        }

        BasicDBObject object = new BasicDBObject();

        object.put("email", emailField.getText());

        DBCursor cursor = Database.getCollection().find(object);

        if (cursor.hasNext()) {
            errorLabel.setText("This email is already registered");
            return;
        }

        if (!passwordField.getText().equals(secondPasswordField.getText())) {
            errorLabel.setText("The passwords does not match");
            return;
        }

        String salt = "qwertyuiop";

        object.put("password", DigestUtils.sha256Hex(passwordField.getText()+salt));

        object.put("confirmed", false);

        Database.getCollection().insert(object);

        User.setUser(object);

        User.setLoggedIn(true);

        User.setEmail(emailField.getText());

        User.setConfirmed(false);

        App.setRoot("account");

        AccountController controller = App.getFxmlLoader().getController();

        controller.showConfirm();

        controller.updateAccount();
    }

    @FXML
    public void goBack() {
        App.setRoot("welcome");
    }
}
