package com.monopolio.ui;

import com.monopolio.managers.SoundManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.utils.FontUtils;
import javafx.application.Application;
import javafx.scene.effect.Glow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Rappresenta la prima schermata precedente alla registrazione, in cui si sceglie con quale modalità giocare.
 */
public class Mode extends Application {
    private SceneManager sceneManager;

    public Mode(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    FontUtils font = new FontUtils(14);

    // Crea un manager per il suono dei bottoni

    // Definizione colori
    private Color backgroundColor = Color.rgb(0, 18, 51);
    private Color monoColor = Color.rgb(255, 255, 255);
    private Color polioColor = Color.rgb(16, 129, 249);

    /**
     * Crea tutto ciò che rappresenta la grafica della parte precedente alla registrazione del gioco, in cui si sceglie con quale modalità giocare(bottoni, colori, testo).
     * @param modeStage schermata sulla quale viene mostrata la grafica.
     */

    @Override
    public void start(Stage modeStage) {
        // Ombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(50); // Imposta il blur
        shadow.setSpread(0.1); // Imposta l'intensità

        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/buildings.png")));
        modeStage.getIcons().add(img);

        // Creazione del testo "MONO"
        Text textMono = new Text("MONO");
        textMono.setFont(font.getFont());
        textMono.setStyle("-fx-font-size: 90px;");
        textMono.setFill(monoColor);
        textMono.setTranslateY(200);
        textMono.setEffect(shadow);

        // Creazione del testo "POLIO"
        Text textPolio = new Text("POLIO");
        textPolio.setFont(font.getFont());
        textPolio.setStyle("-fx-font-size: 90px;"); // Default font size is 14px
        textPolio.setFill(polioColor);
        textPolio.setTranslateY(200);
        textPolio.setEffect(shadow);

        // Creazione di un layout a griglia per posizionare i testi uno accanto all'altro senza spazi
        HBox textHBox = new HBox();
        textHBox.getChildren().addAll(textMono, textPolio);
        textHBox.setSpacing(5); // Spaziatura tra i testi
        textHBox.setAlignment(Pos.CENTER); // Centra i testi orizzontalmente

        // Glow bottoni
        Glow glowEffect = new Glow(0.3); // Imposta il livello di luminosità

        // Creazione del bottone newGame
        Button newGameButton = new Button("New game");
        newGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        newGameButton.setPrefHeight(50);
        newGameButton.setPrefWidth(230);
        newGameButton.setTranslateY(10);
        newGameButton.setStyle("-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");

        // Creazione del bottone loadGame
        Button loadGameButton = new Button("Load game");
        loadGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        loadGameButton.setPrefHeight(50);
        loadGameButton.setPrefWidth(230);
        loadGameButton.setTranslateY(10);
        loadGameButton.setStyle("-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");

        // Creazione dell'effetto di zoom e glow
        double zoomFactor = 1.1; // Fattore di zoom
        Glow zoomEffect = new Glow(0.3); // Effetto di luminosità
        newGameButton.setOnMouseEntered(e -> {
            newGameButton.setScaleX(zoomFactor);
            newGameButton.setScaleY(zoomFactor);
            newGameButton.setEffect(zoomEffect);
            newGameButton.setEffect(glowEffect);
        });
        newGameButton.setOnMouseExited(e -> {
            newGameButton.setScaleX(1);
            newGameButton.setScaleY(1);
            newGameButton.setEffect(null);
        });

        loadGameButton.setOnMouseEntered(e -> {
            loadGameButton.setScaleX(zoomFactor);
            loadGameButton.setScaleY(zoomFactor);
            loadGameButton.setEffect(zoomEffect);
            loadGameButton.setEffect(glowEffect);
        });
        loadGameButton.setOnMouseExited(e -> {
            loadGameButton.setScaleX(1);
            loadGameButton.setScaleY(1);
            loadGameButton.setEffect(null);
        });

        // Aggiunta dell'ombra ai bottoni
        newGameButton.setEffect(shadow);
        loadGameButton.setEffect(shadow);

        // Creazione del VBox per contenere i bottoni
        VBox vbox = new VBox(40); // Spaziatura di 20 tra i nodi
        vbox.setAlignment(Pos.CENTER); // Centra i suoi figli verticalmente

        vbox.getChildren().addAll(newGameButton, loadGameButton);

        // Creazione del BorderPane contenente tutti gli elementi
        BorderPane root = new BorderPane();

        // Carica l'immagine di sfondo
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/citybkg.png")));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Imposta la larghezza e l'altezza della finestra come dimensioni dell'immagine di sfondo
        backgroundImageView.fitWidthProperty().bind(modeStage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(modeStage.heightProperty());

        // Imposta l'immagine di sfondo al BorderPane
        root.getChildren().add(backgroundImageView);
        root.setTop(textHBox);

        // Posizionamento del VBox al centro del BorderPane
        BorderPane.setAlignment(vbox, Pos.CENTER);
        root.setCenter(vbox);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        modeStage.setTitle("Selezione modalità");
        modeStage.setScene(scene);
        modeStage.setMaximized(true);
        modeStage.setMinHeight(800);
        modeStage.setMinWidth(800);
        modeStage.show();

        // bottone listener per il nuovo gioco
        newGameButton.setOnAction(event -> {
            SoundManager.play();
            sceneManager.showStartScreen(modeStage);
        });

        // bottone listener per il caricamento del gioco
        loadGameButton.setOnAction(event -> {
            SoundManager.play();

        });
    }
}
