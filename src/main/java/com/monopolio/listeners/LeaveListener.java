package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.ui.EndGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Gestisce il click del bottone per terminare a tutti la partita.
 */
public class LeaveListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private SceneManager sceneManager;

    public LeaveListener(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
    }

    /**
     * Dopo il click del bottone termina a tutti la partita.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Are you sure you want to end the game?")) return;
            EndGame endGame = new EndGame(false);
            sceneManager.closePane();
            endGame.start(new Stage());
    }




}
