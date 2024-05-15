package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SoundManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuyListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public BuyListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(gameManager.areDicesRolled()) {
            gameManager.buyPropety();
        } else {
            AlertManager.showError("Non hai ancora finito di tirare i dadi!");
        }
    }
}
