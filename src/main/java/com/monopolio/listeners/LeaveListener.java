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

public class LeaveListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public LeaveListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Are you sure you want to end the game?")) return;
        gameManager.bankrupt(gameManager.currentPlayerIndex());
        if(gameManager.lastPlayer()){
            EndGame endGame = new EndGame(false);
            endGame.start(new Stage());
        }
    }




}
