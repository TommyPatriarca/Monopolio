package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SoundManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DiceListener implements EventHandler<ActionEvent> {
    private DiceButton dice;
    private GameManager gameManager;

    public DiceListener(DiceButton dice, GameManager gameManager) {
        this.dice = dice;
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // Logica per la gestione dei dadi
        SoundManager.dices();
        int rolled = dice.roll();
        if(Monopolio.devMode) {
            System.out.println("the number (" + rolled + ") was rolled");
        }
        dice.colorRed();
        dice.disable();

        // Logica per la gestione del player
        gameManager.getCurrentPlayer().moveForward(rolled);
        if(Monopolio.devMode) {
            System.out.println(gameManager.getCurrentPlayer().getName() + " (Player) was moved to the position (" + gameManager.getCurrentPlayer().getPosition() + ")");
        }

        // Alla fine del round -> dice.setDefault();
    }
}
