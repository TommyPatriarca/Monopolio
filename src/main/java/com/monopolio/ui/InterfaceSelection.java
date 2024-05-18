package com.monopolio.ui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Objects;

public class InterfaceSelection extends Application {
    Image img;

    @Override
    public void start(Stage stage) {
        // Creazione della scritta "SELEZIONE INTERFACCIA"
        Label titleLabel = new Label("SELEZIONE INTERFACCIA");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: linear-gradient(to right, cyan, darkcyan);");
        titleLabel.setFont(Font.font("Arial", 24));

        // Creazione dei bottoni CLI e GUI
        Button cliButton = createButton("CLI");
        Button guiButton = createButton("GUI");

        // Gestione degli eventi di clic
        cliButton.setOnAction(e -> handleCliSelection());
        guiButton.setOnAction(e -> handleGuiSelection());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(titleLabel, cliButton, guiButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));

        // Creazione della scena
        Scene scene = new Scene(layout, 300, 250);

        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
        stage.getIcons().add(img);

        // Configurazione dello stage
        stage.setTitle("Selezione Interfaccia");
        stage.setScene(scene);
        stage.show();
    }

    // Metodo per creare un bottone
    private Button createButton(String text) {
        Button button = new Button(text);

        // Applicazione degli stili per un tema scuro
        button.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 100px; -fx-pref-height: 40px; -fx-background-radius: 20px; -fx-border-radius: 20px;");

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

    // Metodo per gestire la selezione CLI
    private void handleCliSelection() {
        System.out.println("CLI selezionata");
        // Qui puoi aggiungere il codice per avviare l'interfaccia CLI
    }

    // Metodo per gestire la selezione GUI
    private void handleGuiSelection() {
        System.out.println("GUI selezionata");
        // Qui puoi aggiungere il codice per avviare l'interfaccia GUI
    }

    public static void main(String[] args) {
        launch(args);
    }
}
