package com.monopolio.ui;

import com.monopolio.Monopolio;
import com.monopolio.cli.Cli;
import com.monopolio.cli.Controllore;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.managers.SceneManager;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.stage.Stage;

import java.util.Objects;

public class InterfaceSelection extends Application {
    private Controllore controllore = new Controllore();
    private Cli cli = new Cli(controllore);

    Image img;
    Image bkg;
    int selection;
    Stage stage;
    Monopolio monopolio = new Monopolio();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        // Creazione dei bottoni CLI e GUI
        Button cliButton = createButton("CLI");
        Button guiButton = createButton("GUI");
        Button exitButton = createButton("EXIT");

        // Gestione degli eventi di clic
        cliButton.setOnAction(e -> handleCliSelection());
        guiButton.setOnAction(e -> handleGuiSelection());
        exitButton.setOnAction(e -> handleExitSelection());

        cliButton.setTranslateY(100);
        guiButton.setTranslateY(100);
        exitButton.setTranslateY(100);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(cliButton, guiButton, exitButton);
        layout.setAlignment(Pos.CENTER);


        // Caricamento dell'immagine di sfondo
        bkg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/selection2.png")));
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(backgroundImage));

        // Creazione della scena
        Scene scene = new Scene(layout, 500, 650);

        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
        stage.getIcons().add(img);

        // Configurazione dello stage
        stage.setTitle("Selezione Interfaccia");
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

    // Metodo per gestire la selezione CLI
    private void handleCliSelection() {
        Monopolio.setInterfaceType(InterfaceManager.InterfaceType.CLI);
        stage.close();
        monopolio.getCli().start();
    }

    // Metodo per gestire la selezione GUI
    private void handleGuiSelection() {
        Monopolio.setInterfaceType(InterfaceManager.InterfaceType.GUI);
        stage.close();
        monopolio.start(new Stage());
    }

    private void handleExitSelection() {
        System.out.println("EXIT selezionato");
        System.exit(0);
    }

    public int getSelection() {
        return selection;
    }

    public void start(String[] args) {
        launch(args);
    }
}
