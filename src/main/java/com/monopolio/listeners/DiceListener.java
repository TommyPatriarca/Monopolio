package com.monopolio.listeners;

import com.monopolio.board.buttons.DiceButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DiceListener implements EventHandler<ActionEvent> {
    DiceButton dice;

    public DiceListener(DiceButton dice) {
        this.dice = dice;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        dice.roll();
        dice.colorRed();
        // Alla fine del round -> dice.setDefault();
    }
}
