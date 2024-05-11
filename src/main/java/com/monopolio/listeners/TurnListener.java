package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.managers.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class TurnListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public TurnListener(GameManager gameManager) {
        this.gameManager=gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!gameManager.areDicesRolled()) {
            // the player cannot end turn if dices are not rolled.
            return;
        }

        for(int i=0; i<4; i++) {
            if(gameManager.getPlayer(i).isMyTurn()) {
                gameManager.getPlayer(i).setMyTurn(false);
                if(i==3 || gameManager.getPlayer(i+1).getNome().isEmpty()) {
                    gameManager.getPlayer(0).setMyTurn(true);
                } else {
                    gameManager.getPlayer(i+1).setMyTurn(true);
                } // logic to end the player's turn.
                if(Monopolio.devMode) {
                    System.out.println(gameManager.getPlayer(i).getNome() + " (Player) ended the turn");
                }

                // Restore the dices for the next player.
                gameManager.restoreDices();

                break;
            }
        }
    }
}
