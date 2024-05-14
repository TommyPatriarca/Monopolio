package com.monopolio.ui;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.utils.FontUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Rappresenta tutto ciò che è presente nella grafica della parte iniziale di registrazione del gioco.
 */
public class Start extends Application {
    private SceneManager sceneManager;

    public Start(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    FontUtils font = new FontUtils(14);

    // Definizione colori
    private Color backgroundColor = Color.rgb(0, 18, 51);
    private Color monoColor = Color.rgb(255, 255, 255);
    private Color polioColor = Color.rgb(16, 129, 249);

    // Definizione dei colori dei bordi dei text box
    private Color[] borderColors = {Color.GREEN, Color.YELLOW, Color.LIGHTBLUE, Color.PURPLE};

    /**
     * Crea tutto ciò che rappresenta la grafica della parte di registrazione del gioco (bottoni, colori, testo):
     * @param primaryStage schermata sulla quale viene mostrata la grafica.
     */
    @Override
    public void start(Stage primaryStage) {
        // Ombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(50); // Imposta il blur
        shadow.setSpread(0.1); // Imposta l'intensità

        // Glow bottoni
        Glow glowEffect = new Glow(0.3); // Imposta il livello di luminosità

        // Creazione del testo "MONO"
        Text textMono = new Text("MONO");
        textMono.setFont(font.getFont());
        textMono.setStyle("-fx-font-size: 90px;");
        textMono.setFill(monoColor);
        textMono.setTranslateY(100);
        textMono.setEffect(shadow);

        // Creazione del testo "POLIO"
        Text textPolio = new Text("POLIO");
        textPolio.setFont(font.getFont());
        textPolio.setStyle("-fx-font-size: 90px;"); // Default font size is 14px
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
        Set<String> playerNamesSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Add Player");
            textField.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
            textField.setStyle("-fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-radius: 10;");
            textField.setFocusTraversable(true);

            // Imposta il colore del bordo in base all'indice
            textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-width: 2.5; -fx-border-color: " + toHex(borderColors[i]) + "; -fx-border-radius: 10;");

            // Limita la lunghezza massima del testo a 10 caratteri
            final int index = i;
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 10) {
                    textField.setText(oldValue);
                }
                // Controlla i nomi duplicati e imposta il bordo rosso se necessario
                if (DuplicateNames(playerFields)) {
                    textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-width: 2.5; -fx-border-color: red; -fx-border-radius: 10;");
                } else {
                    textField.setStyle("-fx-background-color: #001845FF; -fx-text-inner-color: white; -fx-background-radius: 10; -fx-border-width: 2.5; -fx-border-color: " + toHex(borderColors[index]) + "; -fx-border-radius: 10;");
                }
            });

            textField.setOnMouseClicked(e -> {
                if (textField.getText().equals("Add Player")) {
                    textField.setText("");
                }
            });
            textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal && textField.getText().isEmpty()) {
                    textField.setText("");
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
        startButton.setStyle("-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 30;");
        startButton.setDisable(true);
        startButton.setOnMouseEntered(e -> startButton.setEffect(glowEffect));
        startButton.setOnMouseExited(e -> startButton.setEffect(null));

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
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(800);
        primaryStage.show();

        // Aggiungi un listener al pulsante Start per verificare i nomi duplicati
        startButton.setOnAction(event -> {
            String[] playerNames = new String[4];
            for (int i = 0; i < playerFields.length; i++) {
                playerNames[i] = playerFields[i].getText().trim();
            }
            if (DuplicateNames(playerFields)) {
                AlertManager.showError(primaryStage, "Presenza di nomi duplicati!");
            } else {
                SoundManager.play();
                sceneManager.showGameScreen(primaryStage, playerNames); // Mostra la schermata di selezione dei giocatori
            }
        });
    }

    /**
     * Viene utilizzato per convertire un colore in formato esadecimale.
     * @param color il colore del quale si vuole convertire il formato.
     * @return il codice del colore.
     */
    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }


    /**
     * Controlla se ci sono nomi duplicati fra i giocatori.
     * @param fields array di "TextField" che contiene i nomi dei giocatori(se presenti).
     * @return "true" se non sono presenti nomi duplicati, "false" se sono presenti.
     *
     * !  Questo metodo è stato preso da StackOverflow  !.
     */
    private boolean DuplicateNames(TextField[] fields) {
        Set<String> uniqueNames = new HashSet<>();
        for (TextField field : fields) {
            if (!field.getText().isEmpty() && !uniqueNames.add(field.getText().trim())) {
                return true;
            }
        }
        return false;
    }
}
