package com.monopolio;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonListener implements EventHandler<ActionEvent> {

    private String buttonLabel;

    public ButtonListener(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Hai premuto il pulsante: " + buttonLabel);
        // Aggiungi qui le azioni da eseguire quando viene premuto il pulsante
    }
}
