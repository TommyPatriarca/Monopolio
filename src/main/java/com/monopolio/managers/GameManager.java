package com.monopolio.managers;

import com.monopolio.Monopolio;
import com.monopolio.board.Box;
import com.monopolio.board.Groups;
import com.monopolio.board.boxes.*;
import com.monopolio.board.buttons.ChancesButton;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.board.buttons.TreasuresButton;
import com.monopolio.cli.Cli;
import com.monopolio.player.Player;
import com.monopolio.ui.Game;
import com.monopolio.utils.RandUtils;

public class GameManager {
    private Player[] players = new Player[4];
    private Box[] cities = new Box[32]; // 0-31
    private DiceButton[] dices = new DiceButton[2];
    private ChancesButton chancesButton = new ChancesButton();
    private TreasuresButton treasuresButton = new TreasuresButton();

    // CLI
    public GameManager() {

    }

    // GUI
    private Game game;

    public GameManager(Game game) {
        chancesButton = new ChancesButton(this, game);
        treasuresButton = new TreasuresButton(this, game);
        this.game = game;
    }

    public void startGame() {
        for (Player player : players) {
            player.setOldPosition(0);
            player.setPosition(0);
        }
        players[0].setMyTurn(true);
    }

    public Player getCurrentPlayer() {
        for (Player player : players) {
            if (player.isMyTurn()) {
                return player;
            }
        }
        return null;
    }

    public int getDicesRoll() {
        return getDice(0).getValue() + getDice(1).getValue();
    }

    public boolean isDoubleDices() {
        if (areDicesRolled()) {
            return getDice(0).getValue() == getDice(1).getValue();
        }
        return false;
    }

    public boolean areDicesRolled() {
        boolean rolled = true;
        for (DiceButton dice : dices) {
            if (!dice.isRolled()) {
                return false;
            }
        }
        return rolled;
    }

    public void restoreDices() {
        for (DiceButton dice : dices) {
            dice.enable();
        }
    }

    // Handles the player sell propety
    public boolean sellPropety(Box box) {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        // Chances
        if (box instanceof Chances) {
            AlertManager.showError("Non puoi vendere le probabilità...");

            // City
        } else if (box instanceof City) {
            City city = (City) box;
            if (city.isOwned()) {
                if (city.getOwner() == player) {
                    city.sellPropriety(player);
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        game.getLogManager().log(player.getName() + " ha venduto " + city.getNome());
                        sellOutline();
                    } else {
                        Cli.message("\033[0;33m" + "Proprietà " + city.getNome() + " venduta con successo" + "\033[0m");
                    }
                    return true;
                } else {
                    AlertManager.showError("Non sei il proprietario di questa casa");
                }
            } else {
                AlertManager.showError("Questa città non è stata ancora acquistata");
            }

            // Parking
        } else if (box instanceof Parking) {
            AlertManager.showError("Non puoi vendere il parcheggio...");

            // Prison
        } else if (box instanceof Prison) {
            AlertManager.showError("Non puoi vendere la prigione...");

            // StartBox
        } else if (box instanceof StartBox) {
            AlertManager.showError("Non puoi vendere il via...");

            // Stations
        } else if (box instanceof Stations) {
            Stations station = (Stations) box;
            if (station.isOwned()) {
                if (station.getOwner() == player) {
                    station.sellStation(player);
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        game.getLogManager().log(player.getName() + " ha venduto " + station.getNome().replace("\n", " "));
                        sellOutline();
                    } else {
                        Cli.message("\033[0;33m" + "Stazione " + station.getNome().replace("\n", " ") + " venduta con successo" + "\033[0m");
                    }
                    return true;
                } else {
                    AlertManager.showError("Non sei il proprietario di questa stazione");
                }
            } else {
                AlertManager.showError("Questa città non è stata ancora acquistata");
            }

            // Taxes
        } else if (box instanceof Taxes) {
            AlertManager.showError("Non puoi vendere le tasse...");

            // ToPrison
        } else if (box instanceof ToPrison) {
            AlertManager.showError("Non puoi vendere la prigione...");

            // Treasures
        } else if (box instanceof Treasures) {
            AlertManager.showError("Non puoi vendere i tesori...");
            // Todo: implement treasures
        } else {
            if (Monopolio.isDevMode()) {
                System.out.println("Could not handle property sell");
            }
        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }

        return false;
    }

    // Handles the player buy propety
    public boolean buyPropety() {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        // Chances
        if (cities[position] instanceof Chances) {
            AlertManager.showError("Non puoi comprare le probabilità...");

            // City
        } else if (cities[position] instanceof City) {
            City city = (City) cities[position];
            if (!city.isOwned()) {
                if (player.getMoney() >= city.getPrice()) {
                    city.buyPropriety(player);
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        game.getLogManager().log(player.getName() + " ha acquistato " + city.getNome());
                        if (game.getSelectedButtonIndex() != position) {
                            buyOutline(player, position);
                        }
                    } else {
                        Cli.message("\033[0;33m" + "Proprietà " + city.getNome() + " comprata con successo" + "\033[0m");
                    }
                    if (Monopolio.isDevMode()) {
                        System.out.println(player.getName() + " (Player) has bought a propety and now has $" + player.getMoney());
                    }
                    return true;
                } else {
                    AlertManager.showError("Non hai abbastanza soldi per comprare questa citta");
                }
            } else {
                AlertManager.showError("Questa città è gia stata acquistata");
            }

            // Parking
        } else if (cities[position] instanceof Parking) {
            AlertManager.showError("Non puoi comprare il parcheggio...");

            // Prison
        } else if (cities[position] instanceof Prison) {
            AlertManager.showError("Non puoi comprare la prigione...");

            // StartBox
        } else if (cities[position] instanceof StartBox) {
            AlertManager.showError("Non puoi comprare il via...");

            // Stations
        } else if (cities[position] instanceof Stations) {
            Stations station = (Stations) cities[position];
            if (!station.isOwned()) {
                if (player.getMoney() >= station.getPrice()) {
                    station.buyStation(player);
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        game.getLogManager().log(player.getName() + " ha acquistato " + station.getNome().replace("\n", " "));
                        if (game.getSelectedButtonIndex() != position) {
                            buyOutline(player, position);
                        }
                    } else {
                        Cli.message("\033[0;33m" + "Stazione " + station.getNome().replace("\n", " ") + " comprata con successo" + "\033[0m");
                    }
                    if (Monopolio.isDevMode()) {
                        System.out.println(player.getName() + " (Player) has bought a propety and now has $" + player.getMoney());
                    }
                    return true;
                } else {
                    AlertManager.showError("Non hai abbastanza soldi per comprare questa stazione");
                }
            } else {
                AlertManager.showError("Questa stazione è gia stata acquistata");
            }

            // Taxes
        } else if (cities[position] instanceof Taxes) {
            AlertManager.showError("Non puoi comprare le tasse...");

            // ToPrison
        } else if (cities[position] instanceof ToPrison) {
            AlertManager.showError("Non puoi comprare la prigione...");

            // Treasures
        } else if (cities[position] instanceof Treasures) {
            AlertManager.showError("Non puoi comprare i tesori...");
            // Todo: implement treasures
        } else {
            if (Monopolio.isDevMode()) {
                System.out.println("Could not handle buy property");
            }
        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }

        return false;
    }


    // Handles the player position once dices are rolled
    public void handleMovement() {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        // Chances
        if (cities[position] instanceof Chances) {
            Chances chance = (Chances) cities[position];
            // do nothing
            // extractChance(chance.pickRandomIndex(), player);


            // City
        } else if (cities[position] instanceof City) {
            City city = (City) cities[position];
            if (city.isOwned() && city.getOwner() != player) {
                city.getPaid(player);
            } else {
                // Buy or Auction, smh
            }

            // Parking
        } else if (cities[position] instanceof Parking) {
            Parking Parking = (Parking) cities[position];
            // Do nothing

            // Prison
        } else if (cities[position] instanceof Prison) {
            Prison prison = (Prison) cities[position];
            // Do nothing

            // StartBox
        } else if (cities[position] instanceof StartBox) {
            StartBox startBox = (StartBox) cities[position];
            startBox.redeemStart(player);

            // Stations
        } else if (cities[position] instanceof Stations) {
            Stations stations = (Stations) cities[position];
            // Todo: implement stations

            // Taxes
        } else if (cities[position] instanceof Taxes) {
            Taxes taxes = (Taxes) cities[position];
            taxes.redeemTaxes(player);
        } else if (cities[position] instanceof ToPrison) {
            ToPrison toPrison = (ToPrison) cities[position];
            toPrison.toPrison(player);
        } else if (cities[position] instanceof Treasures) {
            Treasures treasures = (Treasures) cities[position];
            // Todo: implement treasures
        } else {
            if (Monopolio.isDevMode()) {
                System.out.println("Could not handle player movement");
            }
        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }
    }

    public void initCity(int number) {
        switch (number) {
            case 0:
                setCity(number, new StartBox(200));
                break;
            case 1:
                setCity(number, new City(Groups.YELLOW, "Traona", 60, 50, 200, 10));
                break;
            case 2:
                setCity(number, new Chances());
                break;
            case 3:
                setCity(number, new City(Groups.YELLOW, "Andalo", 60, 50, 200, 10));
                break;
            case 4:
                setCity(number, new Stations(Stations.StationTypes.NORD, 200, 50));
                break;
            case 5:
                setCity(number, new City(Groups.ORANGE, "Regoledo", 100, 50, 200, 10));
                break;
            case 6:
                setCity(number, new City(Groups.ORANGE, "Talamona", 120, 50, 200, 10));
                break;
            case 7:
                setCity(number, new City(Groups.ORANGE, "Morbegno", 100, 50, 200, 10));
                break;
            case 8:
                setCity(number, new Prison());
                break;
            case 9:
                setCity(number, new City(Groups.RED, "Ardenno", 140, 50, 200, 10));
                break;
            case 10:
                setCity(number, new City(Groups.RED, "Ardenno", 140, 50, 200, 10));
                break;
            case 11:
                setCity(number, new City(Groups.RED, "Berbenno", 140, 50, 200, 10));
                break;
            case 12:
                setCity(number, new Stations(Stations.StationTypes.EST, 200, 50));
                break;
            case 13:
                setCity(number, new City(Groups.PINK, "Castione", 160, 50, 200, 10));
                break;
            case 14:
                setCity(number, new Treasures());
                break;
            case 15:
                setCity(number, new City(Groups.PINK, "Castiones", 160, 50, 200, 10));
                break;
            case 16:
                setCity(number, new Parking());
                break;
            case 17:
                setCity(number, new City(Groups.PURPLE, "Sondrio", 180, 50, 200, 10));
                break;
            case 18:
                setCity(number, new City(Groups.PURPLE, "Chiesa", 180, 50, 200, 10));
                break;
            case 19:
                setCity(number, new City(Groups.PURPLE, "Piantedo", 220, 50, 200, 10));
                break;
            case 20:
                setCity(number, new Stations(Stations.StationTypes.SUD, 200, 50));
                break;
            case 21:
                setCity(number, new City(Groups.GREEN, "San Giacomo", 220, 50, 200, 10));
                break;
            case 22:
                setCity(number, new Chances());
                break;
            case 23:
                setCity(number, new City(Groups.GREEN, "Tirano", 260, 50, 200, 10));
                break;
            case 24:
                setCity(number, new ToPrison());
                break;
            case 25:
                setCity(number, new City(Groups.CYAN, "Livigno", 300, 50, 200, 10));
                break;
            case 26:
                setCity(number, new City(Groups.CYAN, "Sondalo", 280, 50, 200, 10));
                break;
            case 27:
                setCity(number, new City(Groups.CYAN, "Grosio", 260, 50, 200, 10));
                break;
            case 28:
                setCity(number, new Stations(Stations.StationTypes.OVEST, 200, 50));
                break;
            case 29:
                setCity(number, new City(Groups.BLUE, "Trepalle", 300, 50, 200, 10));
                break;
            case 30:
                setCity(number, new Taxes(200));
                break;
            case 31:
                setCity(number, new City(Groups.BLUE, "Bormio", 300, 50, 200, 10));
                break;

            default:
                if (Monopolio.isDevMode()) {
                    System.out.println("Could not find city....");
                }
        }
    }

    private void sellOutline() {
        game.getButton(game.getSelectedButtonIndex()).setStyle("-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
        game.setSelectedButton(null);
        game.setSelectedButtonIndex(0);
    }

    public void buyOutline(Player player, int position) {
        for (int i = 0; i < getPlayers().length; i++) {
            if (getPlayer(i) == player) {
                switch (i) {
                    case 0:
                        game.getButton(position).setStyle("-fx-cursor: hand; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
                        break;
                    case 1:
                        game.getButton(position).setStyle("-fx-cursor: hand; -fx-border-color: yellow; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
                        break;
                    case 2:
                        game.getButton(position).setStyle("-fx-cursor: hand; -fx-border-color: cyan; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
                        break;
                    case 3:
                        game.getButton(position).setStyle("-fx-cursor: hand; -fx-border-color: purple; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
                        break;

                    default:
                        game.getButton(position).setStyle("-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
                        break;
                }

                break;
            }
        }
    }

    public boolean isPropertyOwned(int index) {
        // City
        if (cities[index] instanceof City) {
            City city = (City) cities[index];
            if (city.isOwned()) {
                return true;
            }
        }
        return false;
    }

    public Player getPropertyOwner(int index) {
        // City
        if (cities[index] instanceof City) {
            City city = (City) cities[index];
            if (city.isOwned()) {
                return city.getOwner();
            }
        }

        return null;
    }

    public int getPropertyOwnerIndex(int index) {
        // City
        if (cities[index] instanceof City) {
            City city = (City) cities[index];
            if (city.isOwned()) {
                for (int i = 0; i < players.length; i++) {
                    if (getPlayer(i) == city.getOwner()) {
                        return i;
                    }
                }
            }
        } else
            // Station
            if (cities[index] instanceof Stations) {
                Stations station = (Stations) cities[index];
                if (station.isOwned()) {
                    for (int i = 0; i < players.length; i++) {
                        if (getPlayer(i) == station.getOwner()) {
                            return i;
                        }
                    }
                }
            }

        return -1;
    }

    public int throwDice() {
        return RandUtils.Integer(1, 4);
    }


    /**
     * Controlla se i nomi dei giocatori inseriti sono doppi.
     * @param players è l'insieme dei giocatori.
     * @return "true" se ci sono dei nomi dupplicati, " false" se non ci sono numeri duplicati.
     */
    public boolean duplicateNames(Player[] players) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (players[i].getName().equals(players[j].getName()) && i != j && !(players[i].getName().isEmpty() || players[j].getName().isEmpty())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gestisce il comportamento associato ad una probabilità.
     */
    public void extractChance(int index, Player player) {
        switch (index) {
            case 0:
                // Ottieni 100 monete.
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $100");
                }
                player.addMoney(100);
                break;
            case 1:
                // Vai alla casella Partenza.
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato al via");
                }
                player.setPosition(0);
                break;
            case 2:
                // Vai in prigione.
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato in prigione");
                }
                player.setInPrison(true);
                break;
            case 3:
                // Paga 50 monete di multa.
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha perso $50");
                }
                player.removeMoney(50);
                break;
            case 4:
                // Avanza di tre caselle.
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato avanti di 3 caselle");
                }
                player.setPosition(player.getPosition()+3);
                break;
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
