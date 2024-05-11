package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.SoundManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DiceListener implements EventHandler<ActionEvent> {
    DiceButton dice;
    SoundManager soundManager = new SoundManager();

    public DiceListener(DiceButton dice) {
        this.dice = dice;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        soundManager.dices();
        int rolled = dice.roll();
        if(Monopolio.devMode) {
            System.out.println("the number (" + rolled + ") was rolled");
        }
        dice.colorRed();
        dice.disable();
        // Alla fine del round -> dice.setDefault();
    }
}
