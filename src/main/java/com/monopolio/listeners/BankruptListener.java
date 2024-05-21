package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BankruptListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Game game;

    public BankruptListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        AlertManager.showDialog("Are you sure you want to bankrupt? you will lose the game");
    }
}
