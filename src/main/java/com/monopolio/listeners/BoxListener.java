package com.monopolio.listeners;

import com.monopolio.Monopolio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BoxListener implements EventHandler<ActionEvent> {

    private String buttonLabel;

    public BoxListener(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    @Override
    public void handle(ActionEvent event) {
        if(Monopolio.devMode) {
            System.out.println("the button (" + buttonLabel + ") got clicked");
        }
        // Aggiungi qui le azioni da eseguire quando viene premuto il pulsante
    }
}
