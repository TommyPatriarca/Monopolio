module com.example.demosmonopolio {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.monopolio to javafx.fxml;
    exports com.monopolio;
}