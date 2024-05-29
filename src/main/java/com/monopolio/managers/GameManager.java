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
import javafx.scene.control.Alert;

import java.io.Serializable;

/**
 * Gestisce tutta la logica del gioco.
 */
public class GameManager implements Serializable {
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

    /**
     * Inizializza le posizioni dei player a 0.
     */
    public void startGame() {
        for (Player player : players) {
            player.setOldPosition(0);
            player.setPosition(0);
        }
        players[0].setMyTurn(true);
    }

    /**
     * Cerca il giocatore attuale.
     * @return il giocatore attuale.
     */
    public Player getCurrentPlayer() {
        for (Player player : players) {
            if (player.isMyTurn()) {
                return player;
            }
        }
        return null;
    }

    /**
     * @return il valore del dado tirato.
     */
    public int getDicesRoll() {
        return getDice(0).getValue() + getDice(1).getValue();
    }

    /**
     * Controlla se il numero uscito su entrambi i dadi è uguale.
     * @return il numero uscito sui dadi, "false" se il numero sui 2 dadi è diverso.
     */
    public boolean isDoubleDices() {
        if (areDicesRolled()) {
            return getDice(0).getValue() == getDice(1).getValue();
        }
        return false;
    }

    /**
     * Controlla se i dadi sono stati tirati.
     * @return "false" se i dadi non sono stati tirati, "true" se i dadi sono stati tirati
     */
    public boolean areDicesRolled() {
        boolean rolled = true;
        for (DiceButton dice : dices) {
            if (!dice.isRolled()) {
                return false;
            }
        }
        return rolled;
    }

    /**
     * Resetta i dadi ad ogni turno.
     */
    public void restoreDices() {
        for (DiceButton dice : dices) {
            dice.enable();
        }
    }

    /**
     * Gestisce la vendita delle proprietà.
     * @param box è la casella che si vuole vendere.
     * @return "true" se la proprietà è stata venduta, "false" se la proprietà non può essere venduta.
     */
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

    /**
     * Gestisce la vendita delle case.
     * @param box è la casella che si vuole vendere.
     * @return "true" se la casa è stata venduta, "false" se la casa non può essere venduta.
     */
    public boolean sellHouse(Box box) {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        if (box instanceof City) {
            City city = (City) box;
            if (city.isOwned()) {
                if (city.getOwner() == player) {
                    if (hasTripletCities(city.getGroup(), player)) {
                        if (city.getHouseNumber() <= 0) {
                            if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                                AlertManager.showError("Non hai piu case da vendere");
                            } else {
                                Cli.messageRed("Non hai piu case da vendere");
                            }
                        } else {
                            city.sellHouse(player);
                            if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                                game.getLogManager().log(getCurrentPlayer().getName() + " ha venduto una casa a " + city.getNome());
                            } else {
                                Cli.messageRed("Casa a" + city.getNome() + " con successo");
                            }
                        }
                    } else {
                        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                            AlertManager.showError("Non poessiedi tutte le proprietà di questo gruppo");
                        } else {
                            Cli.messageRed("Non poessiedi tutte le proprietà di questo gruppo");
                        }
                    }
                } else {
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        AlertManager.showError("Non sei il proprietario della città");
                    } else {
                        Cli.messageRed("Non sei il proprietario della città");
                    }
                }
            } else {
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    AlertManager.showError("Questa città non ha ancora un proprietario");
                } else {
                    Cli.messageRed("Questa città non ha ancora un proprietario");
                }
            }
        } else {
            if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                AlertManager.showError("Non hai selezionato una città");
            } else {
                Cli.messageRed("Non hai selezionato una città");
            }

        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }

        return false;
    }

    /**
     * Gestisce l'acquisto delle case.
     * @param box è la casella che si vuole comprare.
     * @return "true" se la casa è stata comprata, "false" se la casa non può essere comprata.
     */
    public boolean buyHouse(Box box) {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        if (box instanceof City) {
            City city = (City) box;
            if (city.isOwned()) {
                if (city.getOwner() == player) {
                    if (hasTripletCities(city.getGroup(), player)) {
                        if (player.getMoney() >= city.getHousePrice(city.getHouseNumber() + 1)) {
                            if (city.getHouseNumber() >= 5) {
                                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                                    AlertManager.showError("Hai raggiunto il limite di case");
                                } else {
                                    Cli.messageRed("Hai raggiunto il massimo di case disponibili per quella città");
                                }
                            } else {
                                city.buyHouse(player);
                                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                                    game.getLogManager().log(getCurrentPlayer().getName() + " ha comprato una casa a " + city.getNome());
                                } else {
                                    Cli.message("\033[0;33m" + "Casa comprata con successo a " + city.getNome() + "\033[0m");
                                }
                            }
                        } else {
                            if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                                AlertManager.showError("Non hai abbastanza soldi per comprare la casa");
                            } else {
                                Cli.messageRed("Non hai abbastanza soldi per comprare la casa");
                            }
                        }
                    } else {
                        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                            AlertManager.showError("Non poessiedi tutte le proprietà di questo gruppo");
                        } else {
                            Cli.messageRed("Non poessiedi tutte le proprietà di questo gruppo");
                        }
                    }
                } else {
                    if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                        AlertManager.showError("Non sei il proprietario della città");
                    } else {
                        Cli.messageRed("Non sei il proprietario della città");
                    }
                }
            } else {
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    AlertManager.showError("Questa città non ha ancora un proprietario");
                } else {
                    Cli.messageRed("Questa città non ha ancora un proprietario");
                }
            }
        } else {
            AlertManager.showError("Non hai selezionato una città");
        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }

        return false;
    }

    /**
     * Gestisce l'acquisto delle proprietà.
     * @return "true" se la proprietà è stata comprata, "false" se la proprietà non può essere comprata.
     */
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

    /**
     * Gestisce la posizione dei player dopo aver tiarto i dadi.
     */
    public void handleMovement() {
        Player player = getCurrentPlayer();
        int position = player.getPosition();

        // Chances
        if (cities[position] instanceof Chances) {
            Chances chance = (Chances) cities[position];
            // do nothing
            extractChance(chance.pickRandomIndex(), player);


            // City
        } else if (cities[position] instanceof City) {
            City city = (City) cities[position];
            if (city.isOwned() && city.getOwner() != player) {
                city.getPaid(player);
                city.getOwner().addMoney(city.getPayment(city.getHouseNumber()));
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
            //startBox.redeemStart(player);

            // Stations
        } else if (cities[position] instanceof Stations) {
            Stations station = (Stations) cities[position];
            if (station.isOwned() && station.getOwner() != player) {
                station.getPaid(getStationsOwned(player), player);
                station.getOwner().addMoney(station.getBasePayment());
                game.getLogManager().log(player.getName() + "ha pagato $" + getStationsOwned(player) * station.getBasePayment() + " a " + station.getOwner().getName());
            }

            // Taxes
        } else if (cities[position] instanceof Taxes) {
            Taxes taxes = (Taxes) cities[position];
            taxes.redeemTaxes(player);
        } else if (cities[position] instanceof ToPrison) {
            ToPrison toPrison = (ToPrison) cities[position];
            toPrison.toPrison(player);
        } else if (cities[position] instanceof Treasures) {
            Treasures treasures = (Treasures) cities[position];
            extractTreasure(treasures.pickRandomIndex(), player);
        } else {
            if (Monopolio.isDevMode()) {
                System.out.println("Could not handle player movement");
            }
        }

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
            game.refreshPlayersGUI();
        }
    }

    /**
     * Inizializza le città.
     * @param number serve per indicare il numero delle vare città.
     */
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
                setCity(number, new City(Groups.RED, "Villapinta", 140, 50, 200, 10));
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
                setCity(number, new City(Groups.PINK, "Albosaggia", 160, 50, 200, 10));
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
                setCity(number, new City(Groups.PURPLE, "Caspoggio", 220, 50, 200, 10));
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

    /**
     * Colora il bordo della casella che si vuole vendere.
     */
    private void sellOutline() {
        game.getButton(game.getSelectedButtonIndex()).setStyle("-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
        game.setSelectedButton(null);
        game.setSelectedButtonIndex(0);
    }

    /**
     * Colora il bordo della casella che si vuole comprare.
     * @param player serve per memorizzare quale giocatore ha comprato quella casella
     * @param position indica il numero della casella che si vuole comprare.
     */
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

    /**
     * Controlla se la prorietà è posseduta da qualcuno.
     * @param index è il numero della proprietà che si vuole controllare.
     * @return "true" se è posseduta da qualcuno, "false" se nessuno la possiede.
     */
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

    /**
     * @param index indica il numero della proprietà da cui si vuole ricavare il possessore.
     * @return il nome del giocatore che possiede tale proprietà.
     */
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

    /**
     * Permette di visualizziare quali stazioni il giocatore possiede.
     * @param player si riferisce al giocatore.
     * @return la stazione posseduta dal giocatore.
     */
    public int getStationsOwned(Player player) {
        // City
        int stations = 0;
        for (Box city : getCities()) {
            if (city instanceof Stations) {
                Stations station = (Stations) city;
                if (station.isOwned() && station.getOwner() == player) {
                    stations++;
                }
            }
        }

        return stations;
    }

    /**
     * @param index è il numero della proprietà.
     * @return il numero del player che possiede la proprietà.
     */
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

    /**
     * Gestisce il tiro dei dadi.
     * @return il valore che il dado assume dopo il suo tiro.
     */
    public int throwDice() {
        return RandUtils.Integer(1, 4);
    }


    /**
     * Controlla se i nomi dei giocatori inseriti sono doppi.
     *
     * @param players è l'insieme dei giocatori.
     * @return "true" se ci sono dei nomi dupplicati, " false" se non ci sono numeri duplicati.
     */
    public boolean duplicateNames(Player[] players) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (players[i].getName().toLowerCase().equals(players[j].getName().toLowerCase()) && i != j && !(players[i].getName().isEmpty() || players[j].getName().isEmpty())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gestisce il comportamento associato ad un tesoro.
     * @param index è il numero del tesoro.
     * @param player indica il gicoatore che pesca la carta tesoro.
     */
    public void extractTreasure(int index, Player player) {
        switch (index) {
            case 0:
                // Ottieni 100 monete.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $20");
                }
                player.addMoney(20);
                break;
            case 1:
                // Vai alla casella Partenza.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $50");
                }
                player.addMoney(50);
                break;
            case 2:
                // Vai in prigione.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $100");
                }
                player.addMoney(100);
                break;
            case 3:
                // Paga 50 monete di multa.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $150");
                }
                player.addMoney(150);
                break;
            case 4:
                // Avanza di tre caselle.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $200");
                }
                player.addMoney(200);
                break;
        }
    }

    /**
     * Gestisce il comportamento associato ad una probabilità.
     * @param index è il numero della probabilità.
     * @param player indica il gicoatore che pesca la carta tesoro.
     */
    public void extractChance(int index, Player player) {
        switch (index) {
            case 0:
                // Ottieni 100 monete.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha ottenuto $100");
                } else {
                    Cli.message("\033[0;32m" + getCurrentPlayer().getName().toUpperCase() + " ha ottenuto un premio per essere passato sulla casella Probabilità" + "\033[0m");
                }
                player.addMoney(100);
                break;
            case 1:
                // Vai alla casella Partenza.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato al via");
                } else {
                    Cli.message("\033[0;32m" + getCurrentPlayer().getName().toUpperCase() + " è stato portato per essere passato sulla casella Probabilità" + "\033[0m");
                }
                player.setPosition(0);
                break;
            case 2:
                // Vai in prigione.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato in prigione");
                } else {
                    Cli.messageRed(getCurrentPlayer().getName().toUpperCase() + " è stato portato in prigione per essere passato sulla casella Probabilità");
                }
                player.setPosition(8);
                player.setInPrison(true);
                break;
            case 3:
                // Paga 50 monete di multa.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + "ha perso $50");
                } else {
                    Cli.messageRed(getCurrentPlayer().getName().toUpperCase() + " ha perso 50$ per essere passato sulla casella Probabilità");
                }
                player.removeMoney(50);
                break;
            case 4:
                // Avanza di tre caselle.
                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI && game != null) {
                    game.getLogManager().log(getCurrentPlayer().getName() + " è stato portato avanti di 3 caselle");
                } else {
                    Cli.message("\033[0;32m" + getCurrentPlayer().getName().toUpperCase() + " è stato portato avanti di 3 caselle per essere passato sulla casella Probabilità" + "\033[0m");
                }
                player.setPosition(player.getPosition() + 3);
                break;
        }
    }

    /**
     * Gestisce la bancarotta.
     * @param index indica il numero del giocatore che sceglie l'opzione bancarotta.
     */
    public void bankrupt(int index) {
        for (int i = 0; i < 4; i++) {
            if (getPlayer(i).isMyTurn()) {
                getPlayer(i).setMyTurn(false);
                if (i == 3 || getPlayer(i + 1).getName().isEmpty()) {
                    getPlayer(0).setMyTurn(true);
                } else {
                    getPlayer(i + 1).setMyTurn(true);
                }

                if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI) {
                    restoreDices();
                }

                break;
            }
        }

        players[index] = new Player("");

        if (Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI) {
            game.removePlayerIcons(players[index], game.getCell(players[index].getOldPosition()));
            game.refreshPlayersGUI();
            game.getLogManager().setMainLog("E' il turno del giocatore: " + getCurrentPlayer().getName());
        }
    }

    /**
     * Gestisce il caso in cui un player possieda 3 città.
     * @param groups sono le città con lo stesso colore.
     * @param player indica il giocatore che possiede tali città.
     * @return "true" se il giocatore possiede 3 città dello stesso colore, "false" se non possiede un gruppo di città.
     */
    public boolean hasTripletCities(Groups groups, Player player) {
        for (Box box : getCities()) {
            if (box instanceof City) {
                City city = (City) box;

                if (city.getGroup() == groups) {
                    if (city.getOwner() == null || city.getOwner() != player) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param index è il numero del giocatore.
     * @return il nome del giocatore.
     */
    public Player getPlayer(int index) {
        return players[index];
    }

    /**
     * @return il nome di tutti i player.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * @param index indica il numero della città.
     * @return la città a cui corrisponde tale numero.
     */
    public Box getCity(int index) {
        return cities[index];
    }

    /**
     * @return tutte le città.
     */
    public Box[] getCities() {
        return cities;
    }

    /**
     * @param index indica il numero del dado.
     * @return il dado a cui corrisponde tale numero.
     */
    public DiceButton getDice(int index) {
        return dices[index];
    }

    /**
     * @return entrambi i dadi.
     */
    public DiceButton[] getDices() {
        return dices;
    }

    /**
     * @return il bottone delle probabilità.
     */
    public ChancesButton getChancesButton() {
        return chancesButton;
    }

    /**
     * @return il bottone dei tesori.
     */
    public TreasuresButton getTreasuresButton() {
        return treasuresButton;
    }

    /**
     * Permette di settare il player in uno slot.
     * @param index è il numero attribuito al player.
     * @param player indica il nome del giocatore.
     */
    public void setPlayer(int index, Player player) {
        this.players[index] = player;
    }

    /**
     * Permette di settare tutti i player.
     * @param players si riferisce al nome dei giocatori.
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Permette di settare le città.
     * @param cities si riferisce ai nomi della città.
     */
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

    public int currentPlayerIndex() {
        for (int i = 0; i < 4; i++) {
            if (players[i].equals(getCurrentPlayer())) {
                return i;
            }
        }
        return 5;
    }

    public boolean lastPlayer() {
        int conta = 0;
        for (int i = 0; i < 4; i++) {
            if (players[i].getName().equals("")) {
                conta++;
            }
        }
        if (conta == 3) {
            return true;
        } else {
            return false;
        }
    }
}
