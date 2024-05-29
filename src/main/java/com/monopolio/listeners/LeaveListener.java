package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.ui.EndGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class LeaveListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private SceneManager sceneManager;

    public LeaveListener(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Are you sure you want to end the game?")) return;
            EndGame endGame = new EndGame(false);
            sceneManager.closePane();
            endGame.start(new Stage());
    }




}
