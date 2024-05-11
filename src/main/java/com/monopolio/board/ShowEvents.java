package com.monopolio.board;

import javafx.scene.control.Label;

public class ShowEvents {
    private String input;

    public ShowEvents(String input) {
        this.input = input;
        // Creazione di un label vuoto
        Label label = new Label();

        // Impostazione della stringa nel label
        label.setText(input);
    }
}
