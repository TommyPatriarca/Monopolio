module com.example.demosmonopolio {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.monopolio to javafx.fxml;
    exports com.monopolio;
    exports com.monopolio.ui;
    opens com.monopolio.ui to javafx.fxml;
    exports com.monopolio.listeners;
    opens com.monopolio.listeners to javafx.fxml;
}