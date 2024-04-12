package com.monopolio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SchermataAvvio extends Application {

    // Definizione colori
    private Color backgroundColor = Color.rgb(0, 18, 51);
    private Color monoColor = Color.rgb(255, 255, 255);
    private Color polioColor = Color.rgb(16, 129, 249);

    @Override
    public void start(Stage primaryStage) {

        // Ombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(50); // Imposta il blur
        shadow.setSpread(0.1); // Imposta l'intensità

        // Creazione del testo "MONO"
        Text textMono = new Text("MONO");
        textMono.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 68));
        textMono.setFill(monoColor);
        textMono.setTranslateY(100);
        textMono.setEffect(shadow);

        // Creazione del testo "POLIO"
        Text textPolio = new Text("POLIO");
        textPolio.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 68));
        textPolio.setFill(polioColor);
        textPolio.setTranslateY(100);
        textPolio.setEffect(shadow);

        // Creazione di un layout a griglia per posizionare i testi uno accanto all'altro senza spazi
        HBox textHBox = new HBox();
        textHBox.getChildren().addAll(textMono, textPolio);
        textHBox.setSpacing(5); // Spaziatura tra i testi
        textHBox.setAlignment(Pos.CENTER); // Centra i testi orizzontalmente

        // Creazione dei campi di testo per i giocatori
        TextField[] playerFields = new TextField[4];
        List<TextField> validFields = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Add Player");
            textField.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
            textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-radius: 10;");
            textField.setFocusTraversable(true);

            // Aggiungi un ChangeListener per il testo del campo
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty() && newValue.matches(".*[^a-zA-Z0-9].*")) {
                    // Se contiene caratteri speciali e non è vuoto, imposta il bordo rosso
                    textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                    validFields.remove(textField);
                } else {
                    // Altrimenti, reimposta lo stile predefinito
                    textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-radius: 10;");
                    if (!newValue.isEmpty() && !validFields.contains(textField)) {
                        // Se il campo non è vuoto e non è già stato aggiunto alla lista dei campi validi, aggiungilo
                        validFields.add(textField);
                    }
                }
            });

            int index = i; // Cattura l'indice corrente per l'utilizzo nell'evento
            textField.setOnMouseClicked(e -> {
                if (textField.getText().equals("Add Player")) {
                    textField.setText("");
                }
            });
            textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal && textField.getText().isEmpty()) {
                    textField.setText("Add Player");
                }
            });
            playerFields[i] = textField;
        }

        // Creazione del bottone start
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(230);
        startButton.setTranslateY(100);
        startButton.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");
        startButton.setDisable(true);

        // Aggiunta dell'ombra al bottone
        startButton.setEffect(shadow);

        // Aggiungi un listener al pulsante Start per mostrare l'avviso se necessario
        startButton.setOnAction(event -> {
            if (validFields.size() >= 2) {
                if (validFields.size() == 4) {
                    showConfirmationAlert(primaryStage);
                } else {
                    showWarningAlert(primaryStage);
                }
            }
        });

        // Creazione del VBox per contenere i campi di testo e il bottone start
        VBox vbox = new VBox(20); // Spaziatura di 20 tra i nodi
        vbox.setAlignment(Pos.CENTER); // Centra i suoi figli verticalmente
        for (TextField textField : playerFields) {
            textField.setMaxWidth(400); // Imposta la larghezza massima in base al rettangolo
            vbox.getChildren().add(textField);
        }
        vbox.getChildren().addAll(startButton);

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
        primaryStage.setTitle("Selezione giocatori");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    // Mostra un avviso di conferma se tutti i campi sono compilati correttamente
    private void showConfirmationAlert(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avviso");
        alert.setHeaderText("Stai per avviare una nuova partita!");
        alert.setContentText("Vuoi procedere?");
        alert.showAndWait();
    }

    // Mostra un avviso di avvertimento se alcuni campi non sono compilati correttamente
    private void showWarningAlert(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avviso");
        alert.setHeaderText("Alcuni campi non sono compilati correttamente!");
        alert.setContentText("Vuoi procedere comunque?");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}