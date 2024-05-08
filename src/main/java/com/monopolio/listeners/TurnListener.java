package com.monopolio.listeners;

import com.monopolio.board.buttons.DiceButton;
import com.monopolio.board.buttons.EndTurnButton;
import com.monopolio.player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TurnListener implements EventHandler<ActionEvent> {
    Player[] players;
    DiceButton[] diceButtons;

    public TurnListener(Player[] players, DiceButton[] diceButtons) {
        this.players = players;
        this.diceButtons = diceButtons;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for(int i=0; i<4; i++) {
            if(players[i].isMyTurn()) {
                players[i].setMyTurn(false);
                diceButtons[0].colorGreen();
                diceButtons[1].colorGreen();
                players[i+1].setMyTurn(true);
            }
        }
    }
}
