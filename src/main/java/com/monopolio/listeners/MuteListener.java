package com.monopolio.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MuteListener implements EventHandler<ActionEvent> {
    private boolean muted;

    public MuteListener() {
        this.muted = false;
    }

    @Override
    public void handle(ActionEvent event) {
        muted = !muted;
        //logica per gestire il mute
    }
}
