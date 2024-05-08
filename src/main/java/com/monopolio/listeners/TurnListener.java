package com.monopolio.listeners;

import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class TurnListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public TurnListener(GameManager gameManager) {
        this.gameManager=gameManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(gameManager.getDice(0).isRolled() && gameManager.getDice(1).isRolled()) {
            for(int i=0; i<4; i++) {
                if(gameManager.getPlayer(i).isMyTurn()) {
                    gameManager.getPlayer(i).setMyTurn(false);
                    gameManager.getDice(0).enable();
                    gameManager.getDice(1).enable();
                    if(i==3) {
                        gameManager.getPlayer(0).setMyTurn(true);
                    } else {
                        gameManager.getPlayer(i+1).setMyTurn(true);
                    }
                }
            }
        }
    }
}
