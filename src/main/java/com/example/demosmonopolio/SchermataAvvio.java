package com.example.demosmonopolio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SchermataAvvio extends Application {

    // Definizione colori
    private Color backgroundColor = Color.rgb(0, 18, 51);
    private Color rectangleColor = Color.rgb(0, 24, 69);
    private Color monoColor = Color.rgb(255, 255, 255);
    private Color polioColor = Color.rgb(16, 129, 249);

    @Override
    public void start(Stage primaryStage) {

        //ombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(50); // Imposta il blur
        shadow.setSpread(0.5); // Imposta l'intensità

        // Creazione del testo "MONO"
        Text textMono = new Text("MONO");
        textMono.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 68));
        textMono.setFill(monoColor);
        textMono.setEffect(shadow);
        textMono.setTranslateY(100); // Abbassa la posizione di 100 pixel

        // Creazione del testo "POLIO"
        Text textPolio = new Text("POLIO");
        textPolio.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 68));
        textPolio.setFill(polioColor);
        textPolio.setEffect(shadow);
        textPolio.setTranslateY(100); // Abbassa la posizione di 100 pixel

        // Creazione di un layout a griglia per posizionare i testi uno accanto all'altro senza spazi
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER); // Posiziona i nodi al centro della griglia
        gridPane.add(textMono, 0, 0);
        gridPane.add(textPolio, 1, 0);

        // Creazione del rettangolo diviso a metà
        Pane rettangoloContainer = new Pane();
        rettangoloContainer.setLayoutX(700); // Posizione x del rettangolo
        rettangoloContainer.setLayoutY(250); // Posizione y del rettangolo
        Rectangle rettangolo = new Rectangle(500, 500);
        rettangolo.setFill(rectangleColor);
        rettangolo.setArcWidth(50); // Impostazione della larghezza dell'arco
        rettangolo.setArcHeight(50); // Impostazione dell'altezza dell'arco

        // Aggiunta dell'ombra al rettangolo
        rettangolo.setEffect(shadow);

        rettangoloContainer.getChildren().add(rettangolo);

        // Creazione delle linee orizzontali sul rettangolo
        for (int i = 1; i < 5; i++) {
            Line linea = new Line();
            linea.setStartX(0);
            linea.setStartY((rettangolo.getHeight() / 5) * i);
            linea.setEndX(rettangolo.getWidth());
            linea.setEndY((rettangolo.getHeight() / 5) * i);
            linea.setStroke(Color.WHITE);
            rettangoloContainer.getChildren().add(linea);
        }


        // Aggiungi etichetta "PLAYERS" nella zona superiore del rettangolo
        Text playersLabel = new Text("PLAYERS");
        playersLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        playersLabel.setFill(Color.WHITE);
        playersLabel.setLayoutX(160); // Posizione X dell'etichetta
        playersLabel.setLayoutY(70); // Posizione Y dell'etichetta
        rettangoloContainer.getChildren().add(playersLabel);

        // Aggiungi campi di testo con la scritta "Add Player" nelle zone successive
        for (int i = 1; i < 5; i++) {
            Text addPlayerText = new Text("+ Add Player");
            addPlayerText.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
            addPlayerText.setFill(Color.WHITE);
            addPlayerText.setLayoutX(rettangolo.getWidth() / 2 - addPlayerText.getBoundsInLocal().getWidth() / 2); // Posizione X del testo al centro del rettangolo
            addPlayerText.setLayoutY((rettangolo.getHeight() / 5) * i + (rettangolo.getHeight() / 10) + 10); // Posizione Y del testo a metà tra lo spazio sopra e quello sotto
            rettangoloContainer.getChildren().add(addPlayerText);
        }


        // Creazione del bottone start
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(230);
        startButton.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");
        // Imposta il colore di sfondo, il colore del testo e i bordi arrotondati

        // Aggiunta dell'ombra al bottone
        startButton.setEffect(shadow);

        BorderPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(startButton, new Insets(0, 0, 200, 0)); // 200 pixel dal fondo

        // Creazione del BorderPane contenente tutti gli elementi
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(gridPane);
        root.getChildren().add(rettangoloContainer); // Aggiungiamo il rettangoloContainer direttamente al root
        root.setBottom(startButton);



        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        primaryStage.setTitle("Selezione giocatori");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
