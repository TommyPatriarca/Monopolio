package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SellListener implements EventHandler<ActionEvent> {
    private Game game;
    private GameManager gameManager;

    public SellListener(Game game, GameManager gameManager) {
        this.gameManager = gameManager;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(game.getSelectedButton() == null) {
            AlertManager.showError("Non ci sono proprietà selezionate");
        } else {
            gameManager.sellPropety(gameManager.getCity(game.getSelectedButtonIndex()));
        }
    }
}
