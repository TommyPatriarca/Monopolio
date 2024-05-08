package com.monopolio.managers;

import com.monopolio.board.Box;
import com.monopolio.board.buttons.ChancesButton;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.board.buttons.TreasuresButton;
import com.monopolio.player.Player;

public class GameManager {
    Player[] players;
    Box[] cities;
    DiceButton[] dices;
    ChancesButton chancesButton;
    TreasuresButton treasuresButton;

    public GameManager(Player[] players, Box[] cities, DiceButton[] dices, ChancesButton chancesButton, TreasuresButton treasuresButton) {
        this.players = players;
        this.cities = cities;
        this.dices = dices;
        this.chancesButton = chancesButton;
        this.treasuresButton = treasuresButton;
    }

    public void startGame() {
        players[0].setMyTurn(true);
    }
}
