package com.monopolio.ui;

import com.monopolio.utils.FontUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class Mode extends Application {
    FontUtils titolo = new FontUtils(14);

    // Definizione colori
    private Color backgroundColor = Color.rgb(0, 18, 51);
    private Color monoColor = Color.rgb(255, 255, 255);
    private Color polioColor = Color.rgb(16, 129, 249);


    @Override
    public void start(Stage modeStage) {
        // Ombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(50); // Imposta il blur
        shadow.setSpread(0.1); // Imposta l'intensità

        // Creazione del testo "MONO"
        Text textMono = new Text("MONO");
        textMono.setFont(titolo.getFont());
        textMono.setStyle("-fx-font-size: 90px;");
        textMono.setFill(monoColor);
        textMono.setTranslateY(200);
        textMono.setEffect(shadow);

        // Creazione del testo "POLIO"
        Text textPolio = new Text("POLIO");
        textPolio.setFont(titolo.getFont());
        textPolio.setStyle("-fx-font-size: 90px;"); // Default font size is 14px
        textPolio.setFill(polioColor);
        textPolio.setTranslateY(200);
        textPolio.setEffect(shadow);

        // Creazione di un layout a griglia per posizionare i testi uno accanto all'altro senza spazi
        HBox textHBox = new HBox();
        textHBox.getChildren().addAll(textMono, textPolio);
        textHBox.setSpacing(5); // Spaziatura tra i testi
        textHBox.setAlignment(Pos.CENTER); // Centra i testi orizzontalmente

        // Creazione del bottone newGame
        Button newGameButton = new Button("New game");
        newGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        newGameButton.setPrefHeight(50);
        newGameButton.setPrefWidth(230);
        newGameButton.setTranslateY(10);
        newGameButton.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");

        // Creazione del bottone newGame
        Button loadGameButton = new Button("Load game");
        loadGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        loadGameButton.setPrefHeight(50);
        loadGameButton.setPrefWidth(230);
        loadGameButton.setTranslateY(10);
        loadGameButton.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");
        
        // Aggiunta dell'ombra al bottone
        newGameButton.setEffect(shadow);
        loadGameButton.setEffect(shadow);

        // Creazione del VBox per contenere i bottoni
        VBox vbox = new VBox(40); // Spaziatura di 20 tra i nodi
        vbox.setAlignment(Pos.CENTER); // Centra i suoi figli verticalmente
        
        vbox.getChildren().addAll(newGameButton);
        vbox.getChildren().addAll(loadGameButton);

        // Creazione del BorderPane contenente tutti gli elementi
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(textHBox);

        // Posizionamento del VBox al centro del BorderPane
        BorderPane.setAlignment(vbox, Pos.CENTER);
        root.setCenter(vbox);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        modeStage.setTitle("Selezione giocatori");
        modeStage.setScene(scene);
        modeStage.setFullScreen(true);
        modeStage.setMinHeight(800);
        modeStage.setMinWidth(800);
        modeStage.show();

        // bottone listener
        Stage primaryStage = new Stage();
        newGameButton.setOnAction(event -> {
            // Copia delle impostazioni della finestra corrente
            primaryStage.setFullScreen(modeStage.isFullScreen());
            primaryStage.setFullScreenExitHint(modeStage.getFullScreenExitHint());
            primaryStage.setFullScreenExitKeyCombination(modeStage.getFullScreenExitKeyCombination());
            primaryStage.setMaximized(modeStage.isMaximized());
            primaryStage.setWidth(modeStage.getWidth());
            primaryStage.setHeight(modeStage.getHeight());
            primaryStage.setX(modeStage.getX());
            primaryStage.setY(modeStage.getY());

            // Apertura dell'interfaccia del gioco sulla nuova finestra
            Start start = new Start();
            try {
                primaryStage.setMinHeight(900);
                primaryStage.setMinWidth(900);
                start.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

            modeStage.close();
            
        });

        // bottone listener
        loadGameButton.setOnAction(event -> {

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
