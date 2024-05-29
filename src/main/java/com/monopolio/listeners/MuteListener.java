package com.monopolio.listeners;

import com.monopolio.board.buttons.MuteButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Gestisce il click del pulsante che disattiva l'audio.
 */
public class MuteListener implements EventHandler<ActionEvent> {
    private MuteButton muteButton;

    public MuteListener(MuteButton muteButton) {
        this.muteButton = muteButton;
    }

    /**
     * Dopo aver cliccato il bottone disattiva l'audio.
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        muteButton.toggle();
    }
}
