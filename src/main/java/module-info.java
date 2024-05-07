module com.example.demosmonopolio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.monopolio to javafx.fxml;
    exports com.monopolio;
    exports com.monopolio.ui;
    opens com.monopolio.ui to javafx.fxml;
    exports com.monopolio.listeners;
    opens com.monopolio.listeners to javafx.fxml;
    exports com.monopolio.player;
    opens com.monopolio.player to javafx.fxml;
    exports com.monopolio.board;
    opens com.monopolio.board to javafx.fxml;
    exports com.monopolio.board.boxes;
    opens com.monopolio.board.boxes to javafx.fxml;
    exports com.monopolio.board.buttons;
    opens com.monopolio.board.buttons to javafx.fxml;
}