package com.monopolio.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertManager {
    public static void showRules(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(primaryStage); // Imposta la finestra genitore
        alert.setTitle("Regole");
        alert.setHeaderText(null);
        alert.setContentText("Obiettivo: Essere l'ultimo giocatore con soldi e proprietà.\n" +
                "Setup: Ogni giocatore inizia con una quantità di denaro e si muove attorno al tabellone acquistando proprietà e pagando affitti.\n" +
                "Turno: Lanci il dado e muovi il tuo pezzo. Puoi acquistare proprietà su cui atterri e costruire case o hotel per aumentare l'affitto.\n" +
                "Affitto: Se atterri su una proprietà di un altro giocatore, devi pagare l'affitto in base alle regole specifiche della proprietà.\n" +
                "Eventi speciali: Atterra su caselle \"Probabilità\" o \"Imprevisti\" per ricevere una carta che può darti vantaggi o svantaggi.\n" +
                "Prigione: Puoi finire in prigione per diverse ragioni. Puoi uscire pagando una multa o tentando di tirare un doppio al dado.\n" +
                "Fallimento: Se non puoi pagare un affitto o una tassa, devi vendere proprietà o dichiararti fallito. L'ultimo giocatore rimasto vince.");

        // Applica lo stile alert personalizzato
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #001845E0; -fx-border-radius: 10;");

        // Rimuove l'intestazione predefinita
        dialogPane.setHeader(null);

        dialogPane.getStyleClass().add("custom-alert");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);

        // Modifica lo stile del contenuto del messaggio
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 10;");

        // Modifica lo stile dei pulsanti dell'alert
        alert.getButtonTypes().forEach(buttonType -> {
            Button button = (Button) dialogPane.lookupButton(buttonType);
            button.setStyle("-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        alert.showAndWait();
    }
}