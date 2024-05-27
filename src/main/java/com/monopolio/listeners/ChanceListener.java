package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChanceListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Game game;

    public ChanceListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
