package com.monopolio.managers;

import com.monopolio.board.Box;
import com.monopolio.board.buttons.ChancesButton;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.board.buttons.TreasuresButton;
import com.monopolio.player.Player;

public class GameManager {
    private Player[] players = new Player[4];
    private Box[] cities = new Box[32]; // 0-31
    private DiceButton[] dices = new DiceButton[2];
    private ChancesButton chancesButton = new ChancesButton();
    private TreasuresButton treasuresButton = new TreasuresButton();

    public void startGame() {
        for(Player player : players) {
            player.setPosition(0);
        }
        players[0].setMyTurn(true);
    }

    public Player getCurrentPlayer() {
        for(Player player : players) {
            if(player.isMyTurn()) {
                return player;
            }
        }
        return null;
    }

    public boolean areDicesRolled() {
        boolean rolled = true;
        for(DiceButton dice : dices) {
            if(!dice.isRolled()) {
                return false;
            }
        }
        return rolled;
    }

    public void restoreDices() {
        for(DiceButton dice : dices) {
            dice.enable();
        }
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public Player[] getPlayers() {
        return players;
    }

    public Box getCity(int index) {
        return cities[index];
    }

    public Box[] getCities() {
        return cities;
    }

    public DiceButton getDice(int index) {
        return dices[index];
    }

    public DiceButton[] getDices() {
        return dices;
    }

    public ChancesButton getChancesButton() {
        return chancesButton;
    }

    public TreasuresButton getTreasuresButton() {
        return treasuresButton;
    }

    public void setPlayer(int index, Player player) {
        this.players[index] = player;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setCities(Box[] cities) {
        this.cities = cities;
    }

    public void setCity(int index, Box city) {
        this.cities[index] = city;
    }

    public void setDice(int index, DiceButton dice) {
        this.dices[index] = dice;
    }

    public void setDices(DiceButton[] dices) {
        this.dices = dices;
    }

    public void setChancesButton(ChancesButton chancesButton) {
        this.chancesButton = chancesButton;
    }

    public void setTreasuresButton(TreasuresButton treasuresButton) {
        this.treasuresButton = treasuresButton;
    }
}
