package com.monopolio.managers;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.Queue;

public class LogManager {
    private Label logLabel;
    private Queue<String> logQueue;

    public LogManager(Label logLabel) {
        this.logLabel = logLabel;
        this.logQueue = new LinkedList<>();
    }

    public void log(String message) {
        logQueue.offer(message);

        if (logQueue.size() > 3) {
            logQueue.poll();
        }
    }

    private void updateLabelText() {
        StringBuilder sb = new StringBuilder();

        for (String log : logQueue) {
            sb.append(log).append("\n");
        }

        logLabel.setText(sb.toString());

        logLabel.setFont(Font.font(logLabel.getFont().getName(), 10));
    }

    private void design(){

    }

}
