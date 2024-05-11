package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class TurnListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Stage primaryStage;

    public TurnListener(GameManager gameManager, Stage primaryStage) {
        this.gameManager = gameManager;
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!gameManager.areDicesRolled()) {
            // the player cannot end turn if dices are not rolled.
            AlertManager.showError(primaryStage, "Non puoi terminare il turno prima di aver tirato entrambi i dadi.");
            return;
        }

        for(int i=0; i<4; i++) {
            if(gameManager.getPlayer(i).isMyTurn()) {
                gameManager.getPlayer(i).setMyTurn(false);
                if(i==3 || gameManager.getPlayer(i+1).getName().isEmpty()) {
                    gameManager.getPlayer(0).setMyTurn(true);
                } else {
                    gameManager.getPlayer(i+1).setMyTurn(true);
                } // logic to end the player's turn.
                if(Monopolio.devMode) {
                    System.out.println(gameManager.getPlayer(i).getName() + " (Player) ended the turn");
                }

                // Restore the dices for the next player.
                gameManager.restoreDices();

                break;
            }
        }
    }
}
