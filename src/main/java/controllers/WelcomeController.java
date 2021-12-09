package controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.App;
import main.Database;
import main.User;
import org.apache.commons.codec.digest.DigestUtils;

public class WelcomeController {

    @FXML
    private TextField emailField = new TextField();

    @FXML
    private PasswordField passwordField = new PasswordField();

    @FXML
    private Label errorLoginLabel = new Label();

    @FXML
    private Button loginButton = new Button();

    @FXML
    private Button registerButton = new Button();

    @FXML
    private void tryLogin() {
        errorLoginLabel.setText("");
        String email = emailField.getText();
        String salt = "qwertyuiop";
        String password = DigestUtils.sha256Hex(passwordField.getText() + salt);
        passwordField.setText("");

        BasicDBObject user = new BasicDBObject();

        user.put("email", email);

        DBCursor cursor = Database.getCollection().find(user);

        if (!cursor.hasNext()) {

            errorLoginLabel.setText("email / password is incorrect!");
            return;
        }

        DBObject userFound = cursor.next();

        if (!userFound.get("password").equals(password)) {
            errorLoginLabel.setText("email / password is incorrect!");
            return;
        }

        User.setUser((BasicDBObject) userFound);

        User.setEmail(email);

        User.setConfirmed((Boolean) userFound.get("confirmed"));

        User.setLoggedIn(true);

        App.setRoot("account");

        AccountController controller = App.getFxmlLoader().getController();

        if (User.getConfirmed())
            controller.hideConfirm();
        else
            controller.showConfirm();

        controller.updateAccount();

    }

    @FXML
    public void openRegistration() {
        errorLoginLabel.setVisible(false);
        App.setRoot("registration");
    }
}
