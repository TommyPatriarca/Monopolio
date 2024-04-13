package com.monopolio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        // Aggiungi un listener ai campi di testo per controllare se ci sono almeno due campi di testo con testo valido
        for (TextField textField : playerFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty() && newValue.matches("^[a-zA-Z0-9]*$") && !validFields.contains(textField)) {
                    validFields.add(textField);
                } else if (newValue.isEmpty() || !newValue.matches("^[a-zA-Z0-9]*$")) {
                    validFields.remove(textField);
                }
                startButton.setDisable(validFields.size() < 2); // Abilita il pulsante Start solo se ci sono almeno due campi di testo validi
            });
        }

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
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(800);
        primaryStage.show();

        // Aggiungi un listener ai campi di testo per controllare se ci sono almeno due campi di testo con testo valido
        for (TextField textField : playerFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty() && newValue.matches("^[a-zA-Z0-9]*$") && !validFields.contains(textField)) {
                    validFields.add(textField);
                } else if (newValue.isEmpty() || !newValue.matches("^[a-zA-Z0-9]*$")) {
                    validFields.remove(textField);
                }
                startButton.setDisable(validFields.size() < 2); // Abilita il pulsante Start solo se ci sono almeno due campi di testo validi
            });

            // Aggiungi un flag per tracciare se il campo di testo è stato modificato
            boolean[] modified = {false};
            // Aggiungi un listener per controllare il testo quando il campo di testo perde il focus
            textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal && textField.getText().isEmpty()) {
                    if (!modified[0]) {
                        validFields.add(textField);
                        startButton.setDisable(validFields.size() < 2); // Abilita il pulsante Start solo se ci sono almeno due campi di testo validi
                    }
                }
            });

            // Aggiungi un listener per tracciare le modifiche al testo del campo di testo
            textField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (!oldVal.equals(newVal)) {
                    modified[0] = true;
                }
            });
        }

        // Aggiungi un listener al pulsante Start per verificare i nomi duplicati
        startButton.setOnAction(event -> {
            String[] playerNames = new String[4];
            for (int i = 0; i < playerFields.length; i++) {
                playerNames[i] = playerFields[i].getText().trim();
            }
            if (hasDuplicateNames(playerNames)) {
                showAlert(primaryStage);
            } else {
                // Avvia la partita
            }
        });
    }

    // Metodo per verificare se ci sono nomi duplicati tra i giocatori
    private boolean hasDuplicateNames(String[] names) {
        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (!names[i].isEmpty() && names[i].equalsIgnoreCase(names[j]) && !names[i].isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Metodo per mostrare un avviso
    // Mostra un avviso se vengono inseriti nomi duplicati
    private void showAlert(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(primaryStage); // Imposta la finestra genitore
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText("Presenza di nomi duplicati!");

        // Applica lo stile alert personalizzato
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #001845E0; -fx-border-radius: 10;");

        // Rimuove l'intestazione predefinita
        dialogPane.setHeader(null);

        // Arrotonda i bordi dell'alert
        dialogPane.getStyleClass().add("custom-alert");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);

        // Modifica lo stile del contenuto del messaggio
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        // Modifica lo stile dei pulsanti dell'alert
        alert.getButtonTypes().forEach(buttonType -> {
            Button button = (Button) dialogPane.lookupButton(buttonType);
            button.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        alert.showAndWait();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
