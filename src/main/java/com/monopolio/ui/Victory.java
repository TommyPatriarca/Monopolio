package com.monopolio.ui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Victory extends Application {

    @Override
    public void start(Stage stage) {
        // Creazione dei bottoni "Rigioca" ed "Esci"
        Button replayButton = createButton("Rigioca");
        Button exitButton = createButton("Esci");

        // Gestione degli eventi di clic
        replayButton.setOnAction(e -> handleReplaySelection());
        exitButton.setOnAction(e -> handleExitSelection());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(replayButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        // Creazione della scena
        Scene scene = new Scene(layout, 600, 700);

        // Configurazione dello stage
        stage.setTitle("FINE DELLA PARTITA");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Metodo per creare un bottone
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 40px; -fx-background-radius: 20px; -fx-border-radius: 20px;");

        // Aggiunta dell'effetto di Glow
        DropShadow glow = new DropShadow();
        glow.setColor(Color.CYAN);
        glow.setWidth(30);
        glow.setHeight(30);
        button.setOnMouseEntered(e -> button.setEffect(glow));
        button.setOnMouseExited(e -> button.setEffect(null));

        // Aggiunta dell'effetto di Zoom
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        button.setOnMousePressed(e -> scaleTransition.play());
        button.setOnMouseReleased(e -> scaleTransition.stop());

        return button;
    }

    private void handleReplaySelection() {
        System.out.println("Rigioca selezionato");
        // Aggiungi qui la logica per rigiocare
    }

    private void handleExitSelection() {
        System.out.println("Esci selezionato");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
