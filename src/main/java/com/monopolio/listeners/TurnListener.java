package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class TurnListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public TurnListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!gameManager.areDicesRolled()) {
            // the player cannot end turn if dices are not rolled.
            AlertManager.showError("Non puoi terminare il turno prima di aver tirato entrambi i dadi.");
            return;
        }

        if(gameManager.getCurrentPlayer().getMoney() < 0) {
            // the player cannot end turn if dices are not rolled.
            AlertManager.showError("Non puoi terminare il turno con il saldo in negativo");
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
                if(Monopolio.isDevMode()) {
                    System.out.println(gameManager.getPlayer(i).getName() + " (Player) ended the turn");
                }

                // Restore the dices for the next player.
                gameManager.restoreDices();

                break;
            }
        }
    }
}
