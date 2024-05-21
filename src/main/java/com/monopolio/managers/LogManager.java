package com.monopolio.managers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.Queue;

public class LogManager {
    private Label logLabel;
    private Queue<String> logQueue;

    public LogManager(Label logLabel) {
        this.logLabel = logLabel;
        this.logQueue = new LinkedList<>();

        // Imposta l'allineamento del testo al centro
        logLabel.setAlignment(Pos.CENTER);

        // Personalizza lo stile del Label
        logLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        logLabel.setFont(Font.font(logLabel.getFont().getName(), 14)); // Modifica la dimensione del font se necessario
        logLabel.setTextFill(Color.YELLOW); // Modifica il colore del testo se necessario
    }

    public void log(String message) {
        logQueue.offer(message);

        if (logQueue.size() > 3) {
            logQueue.poll();
        }
        updateLabelText();
    }

    private void updateLabelText() {
        StringBuilder sb = new StringBuilder();

        for (String log : logQueue) {
            sb.append(log).append("\n");
        }

        logLabel.setText(sb.toString());
    }
}
