package com.monopolio.listeners;

import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.ButtonSoundManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DiceListener implements EventHandler<ActionEvent> {
    DiceButton dice;
    ButtonSoundManager buttonSoundManager = new ButtonSoundManager();

    public DiceListener(DiceButton dice) {
        this.dice = dice;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        buttonSoundManager.dices();
        dice.roll();
        dice.colorRed();
        dice.disable();
        // Alla fine del round -> dice.setDefault();
    }
}
