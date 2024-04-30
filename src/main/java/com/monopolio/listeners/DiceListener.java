package com.monopolio.listeners;

import com.monopolio.board.Dice;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DiceListener implements EventHandler<ActionEvent> {
    Dice dice;

    public DiceListener(Dice dice) {
        this.dice = dice;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        dice.roll();

        // Alla fine del round -> dice.setDefault();
    }
}
