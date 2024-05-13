package com.monopolio.managers;

import com.monopolio.board.Box;
import com.monopolio.board.Groups;
import com.monopolio.board.boxes.*;
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

    public void handleMovement() {
        if(areDicesRolled()) {

        }
    }
    
    public void initCity(int number) {
        switch (number){
            case 0:
                setCity(number, new StartBox(200));
                break;
            case 1:
                setCity(number, new City(Groups.BLUE, "Traona", 60, 50, 200, 10));
                break;
            case 2:
                setCity(number, new Chances());
                break;
            case 3:
                setCity(number, new City(Groups.BLUE,"Andalo", 60, 50, 200, 10));
                break;
            case 4:
                setCity(number, new Taxes(200));
                break;
            case 5:
                setCity(number, new City(Groups.YELLOW,"Regoledo", 100, 50, 200, 10));
                break;
            case 6:
                setCity(number, new Treasures());
                break;
            case 7:
                setCity(number, new City(Groups.YELLOW,"Morbegno", 100, 50, 200, 10));
                break;
            case 8:
                setCity(number, new Prison());
                break;
            case 9:
                setCity(number, new City(Groups.YELLOW,"Talamona", 120, 50, 200, 10));
                break;
            case 10:
                setCity(number, new City(Groups.ORANGE,"Ardenno", 140, 50, 200, 10));
                break;
            case 11:
                setCity(number, new Stations(Stations.StationTypes.EST));
                break;
            case 12:
                setCity(number, new City(Groups.ORANGE,"Berbenno", 140, 50, 200, 10));
                break;
            case 13:
                setCity(number, new City(Groups.ORANGE,"Castione", 160, 50, 200, 10));
                break;
            case 14:
                setCity(number, new City(Groups.PINK,"Castiones", 160, 50, 200, 10));
                break;
            case 15:
                setCity(number, new Chances());
                break;
            case 16:
                setCity(number, new Parking());
                break;
            case 17:
                setCity(number, new City(Groups.PINK,"Sondrio", 180, 50, 200, 10));
                break;
            case 18:
                setCity(number, new City(Groups.PINK,"Chiesa", 180, 50, 200, 10));
                break;
            case 19:
                setCity(number, new City(Groups.GREEN,"Piantedo", 220, 50, 200, 10));
                break;
            case 20:
                setCity(number, new Treasures());
                break;
            case 21:
                setCity(number, new City(Groups.GREEN,"San Giacomo", 220, 50, 200, 10));
                break;
            case 22:
                setCity(number, new City(Groups.GREEN,"Tresenda", 240, 50, 200, 10));
                break;
            case 23:
                setCity(number, new City(Groups.CYAN,"Tirano", 260, 50, 200, 10));
                break;
            case 24:
                setCity(number, new ToPrison());
                break;
            case 25:
                setCity(number, new Stations(Stations.StationTypes.SUD));
                break;
            case 26:
                setCity(number, new City(Groups.CYAN,"Sondalo", 280, 50, 200, 10));
                break;
            case 27:
                setCity(number, new City(Groups.CYAN,"Grosio", 260, 50, 200, 10));
                break;
            case 28:
                setCity(number, new City(Groups.RED,"Livigno", 300, 50, 200, 10));
                break;
            case 29:
                setCity(number, new City(Groups.RED,"Trepalle", 300, 50, 200, 10));
                break;
            case 30:
                setCity(number, new Chances());
                break;
            case 31:
                setCity(number, new City(Groups.RED,"Bormio", 300, 50, 200, 10));
                break;

            default:
                System.out.println("Could not find city....");
        }
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
