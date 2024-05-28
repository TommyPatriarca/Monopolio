package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.player.Player;
import com.monopolio.ui.EndGame;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BankruptListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Game game;
    private EndGame endGame = new EndGame();

    public BankruptListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Are you sure you want to bankrupt? you will lose the game")) return;
        gameManager.bankrupt(gameManager.currentPlayerIndex());
        if(gameManager.lastPlayer()){

        }
    }




}
