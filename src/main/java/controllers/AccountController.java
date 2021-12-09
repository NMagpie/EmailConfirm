package controllers;

import email.Email;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.App;
import main.User;

import java.io.IOException;
import java.nio.file.Paths;

public class AccountController {

    @FXML
    private Label emailLabel = new Label();

    @FXML
    private Label confirmedLabel = new Label();

    @FXML
    private Button confirmButton = new Button();

    @FXML
    private Button logOutButton = new Button();

    @FXML
    public void confirm() throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Paths.get("src/main/java/controllers/confirm.fxml").toUri().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        ConfirmController controller = fxmlLoader.getController();

        String code = Email.getRandomCode();

        Email.send(code);

        controller.setStage(stage);

        controller.setCode(code);

        controller.setEmailLabel(User.getEmail());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logOut() {
        User.setUser(null);
        User.setEmail("");
        User.setLoggedIn(false);
        User.setConfirmed(false);
        App.setRoot("welcome");
    }

    public void hideConfirm() {
        this.confirmButton.setVisible(false);
    }

    public void showConfirm() {
        this.confirmButton.setVisible(true);
    }

    public void updateAccount() {
        emailLabel.setText(User.getEmail());

        if (User.getConfirmed())
            confirmedLabel.setText("Yes");
        else
            confirmedLabel.setText("No");
    }

}
