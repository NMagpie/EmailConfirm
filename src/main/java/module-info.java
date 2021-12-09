module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires activation;
    requires java.mail;
    requires org.apache.commons.codec;

    opens controllers to javafx.fxml;
    opens main to javafx.fxml;
    exports main;
}
