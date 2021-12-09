package controllers;

import com.mongodb.BasicDBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.App;
import main.Database;
import main.User;

import java.util.concurrent.TimeUnit;

public class ConfirmController {

    @FXML
    private Label emailLabel = new Label();

    @FXML
    private TextField codeField = new TextField();

    @FXML
    private Button okButton = new Button();

    @FXML
    private Label errorCodeLabel = new Label();

    @FXML
    public void confirm() throws InterruptedException {

        AccountController controller = App.getFxmlLoader().getController();

        errorCodeLabel.setText("");

        if (!codeField.getText().equals(code)) {
            errorCodeLabel.setText("Wrong confirmation code!");

            if (User.getConfirmed())
                controller.hideConfirm();
            else
                controller.showConfirm();

        } else {

            errorCodeLabel.setText("Your email was confirmed!");

            User.setConfirmed(true);

            BasicDBObject oldObejct = (BasicDBObject) User.getUser().clone();

            User.getUser().put("confirmed",true);

            Database.getCollection().update(oldObejct,User.getUser());

            controller.updateAccount();

            code = "";

            if (User.getConfirmed())
                controller.hideConfirm();
            else
                controller.showConfirm();

            TimeUnit.SECONDS.sleep(4);

            stage.close();
        }

    }

    private Stage stage = new Stage();

    private String code = "";

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmailLabel(String str) {
        this.emailLabel.setText(str);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}