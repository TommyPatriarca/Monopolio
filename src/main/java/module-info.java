module com.example.demosmonopolio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demosmonopolio to javafx.fxml;
    exports com.example.demosmonopolio;
}