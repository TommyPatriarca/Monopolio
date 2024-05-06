package com.monopolio.listeners;

import com.monopolio.board.buttons.MuteButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MuteListener implements EventHandler<ActionEvent> {
    private MuteButton muteButton;

    public MuteListener(MuteButton muteButton) {
        this.muteButton = muteButton;
    }

    @Override
    public void handle(ActionEvent event) {
        muteButton.toggle();
        //logica per gestire il mute
    }
}
