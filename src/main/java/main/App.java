package main;

import email.Email;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class App extends Application {

    private static Scene mainScene;

    private static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        Database.connectDB();
        stage.setResizable(false);
        mainScene = new Scene(loadFXML("welcome"));
        stage.setScene(mainScene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Paths.get("src/main/java/controllers/"+fxml+".fxml").toUri().toURL());
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml) {
        try {
            mainScene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public static Scene getRoot() {
        return mainScene;
    }

    public static void main(String[] args) {
        launch();
    }

}