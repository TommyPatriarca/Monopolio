package com.monopolio.cli;

import com.monopolio.Monopolio;
import com.monopolio.board.Box;
import com.monopolio.board.boxes.*;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe che permette l'interazione tramite linea di comando con l'utente.
 */
public class Cli {
    private Scanner s = new Scanner(System.in);
    private GameManager gameManager = new GameManager();
    private ArrayList<Box> posssedute1 = new ArrayList<Box>();
    private ArrayList<Box> posssedute2 = new ArrayList<Box>();
    private ArrayList<Box> posssedute3 = new ArrayList<Box>();
    private ArrayList<Box> posssedute4 = new ArrayList<Box>();

    /**
     * Chiede con quale tipologia di interfaccia si vuole utilizzare il gioco.
     *
     * @return il nbumero intero corrispondente alla scelta.
     */
    public int askInterface() {
        int selection = 0;
        do {
            messagePrint("Benvenuto!\nCon che tipo di interfaccia vuoi giocare?\n[1] CLI\n[2] GUI");
            messagePrint("\nSelezione -> ");
            try {
                selection = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                messageRed("Non hai inserto un numero");
            }
        } while (selection != 1 && selection != 2);

        return selection;
    }


    /**
     * Stampa a schermo il titolo del programma e successivamente chiama le funzioni per inizializzare e stampare la griglia.
     */
    public void startCli() {
        messageRed("\n------------------------------------- MONOPOLIO --------------------------------------------\n\n");
        askName();
        initBoard();
        printBoard();
        handle();

    }

    /**
     * Chiede quanti giocatori voglio giocare e gli chiede il nome.
     */
    public void askName() {
        int nPlayer = 0;
        boolean flag2, flag;
        Player[] players = new Player[4];

        do {
            messagePrint("Quanti giocatori vogliono giocare? ( mininimo -> 2 massimo -> 4)");
            messagePrint("\nSelezione -> ");
            try {
                nPlayer = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                messageRed("Non hai inserto un numero");
            }

        } while (!(nPlayer >= 2 && nPlayer <= 4));


        //Inserisce nomi
        switch (nPlayer) {
            case 2:
                flag = true;
                do {
                    messagePrint("\nInserisci il nome del giocatore 1");
                    messagePrint("\nSelezione -> ");
                    String n1 = s.nextLine();

                    Player player1 = new Player(n1);
                    players[0] = player1;

                    messagePrint("\nInserisci il nome del giocatore 2");
                    messagePrint("\nSelezione -> ");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    Player player3 = new Player("");
                    players[2] = player3;
                    Player player4 = new Player("");
                    players[3] = player4;

                    flag2 = gameManager.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        gameManager.setPlayer(1, player2);
                        gameManager.setPlayer(2, player3);
                        gameManager.setPlayer(3, player4);
                        message("\n");
                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;

            case 3:
                flag = true;
                do {
                    messagePrint("\nInserisci il nome del giocatore 1");
                    messagePrint("\nSelezione -> ");
                    String n1 = s.nextLine();
                    Player player1 = new Player(n1);
                    players[0] = player1;

                    messagePrint("\nInserisci il nome del giocatore 2");
                    messagePrint("\nSelezione -> ");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    messagePrint("\nInserisci il nome del giocatore 3");
                    messagePrint("\nSelezione -> ");
                    String n3 = s.nextLine();
                    Player player3 = new Player(n3);
                    players[2] = player3;

                    Player player4 = new Player("");
                    players[3] = player4;

                    flag2 = gameManager.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        gameManager.setPlayer(1, player2);
                        gameManager.setPlayer(2, player3);
                        gameManager.setPlayer(3, player4);
                        message("\n");
                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;

            case 4:
                flag = true;
                do {
                    messagePrint("\nInserisci il nome del giocatore 1");
                    messagePrint("\nSelezione -> ");
                    String n1 = s.nextLine();
                    Player player1 = new Player(n1);
                    players[0] = player1;

                    messagePrint("\nInserisci il nome del giocatore 2");
                    messagePrint("\nSelezione -> ");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    messagePrint("\nInserisci il nome del giocatore 3");
                    messagePrint("\nSelezione -> ");
                    String n3 = s.nextLine();
                    Player player3 = new Player(n3);
                    players[2] = player3;

                    messagePrint("\nInserisci il nome del giocatore 4");
                    messagePrint("\nSelezione -> ");
                    String n4 = s.nextLine();
                    Player player4 = new Player(n4);
                    players[3] = player4;

                    flag2 = gameManager.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        gameManager.setPlayer(1, player2);
                        gameManager.setPlayer(2, player3);
                        gameManager.setPlayer(3, player4);
                        message("\n");
                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;
        }
    }

    /**
     * Inizializza la griglia delle città inserendo le città nelle varie posizioni.
     */
    public void initBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 8 || j == 0 || j == 8) {
                    int number;
                    if (i == 0) {
                        number = j + 1;
                    } else if (j == 8) {
                        number = 9 + i;
                    } else if (i == 8) {
                        number = 25 - j;
                    } else {
                        number = 33 - i;
                    }
                    gameManager.initCity(number - 1);
                }
            }
        }
    }


    /**
     * Chiama tutte le funzioni che permettono lo svolgimento del gioco.
     */
    public void handle() {
        boolean flag = false;
        gameManager.startGame();

        //Todo: fare controllo bancarotta per terminare.
        while (true) {
            askDice();
            flag = false;
            while (!flag) {
                flag = askChoose();
            }
        }
    }

    /**
     * Chiede all'utente di lanciare i dadi e verifica che non sia in prigione.
     */
    public void askDice() {
        boolean flag = false, flag2 = false;
        int d1 = 0, d2 = 0;

        message("\n\n-----------------------------------------------------------------------------");
        message("\033[0;32m" + "TURNO DI " + gameManager.getCurrentPlayer().getName().toUpperCase() + "\033[0m");

        //Todo: deve uscire dopo 1 turno fermo
        if (gameManager.getCurrentPlayer().inPrison()) {
            messageRed("\nSei in prigione! Non potrai avanzare in questo turno");
            message("Vuoi pagare 100$ e uscire subito di prigione? (si/no)");
            messagePrint("Selezione -> ");
            String choose = s.nextLine();

            do {
                if (choose.toLowerCase().trim().equals("si")) {
                    //ESCE DI PRIGIONE
                    message("\033[0;32m" + gameManager.getCurrentPlayer().getName().toUpperCase() + " è uscito di prigione" + "\033[0m");
                    gameManager.getCurrentPlayer().setInPrison(false);
                    gameManager.getCurrentPlayer().removeMoney(100);
                    askDice();
                    flag2 = true;
                } else if (choose.toLowerCase().trim().equals("no")) {
                    flag2 = true;
                }
            } while (!flag2);

        } else {
            do {
                message("\nInserire " + "si" + " per tirare i dadi");
                messagePrint("Selezione -> ");
                String choose = s.nextLine();
                if (choose.toLowerCase().trim().equals("si")) {
                    d1 = gameManager.throwDice();
                    message("\033[0;36m" + "Dado 1 -> " + d1 + "\033[0m");
                    d2 = gameManager.throwDice();
                    message("\033[0;36m" + "Dado 2 -> " + d2 + "\033[0m");
                    message("\033[0;36m" + "Spostamento di " + (d1 + d2) + " caselle" + "\033[0m");
                    flag = true;
                }
            } while (!flag);

            gameManager.getCurrentPlayer().moveForward(d1 + d2);
            //CITY
            if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof City) {
                City city = (City) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                message("\033[0;36m" + "Posizione attuale: " + gameManager.getCity(gameManager.getCurrentPlayer().getPosition()).getNome().replace("\n", " ") + " -> Prezzo: " + city.getPrice() + "$" + "\033[0m");
                if (city.isOwned()) {
                    city.getPaid(gameManager.getCurrentPlayer());
                    messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " ha pagato per essere passato su una proprietà già acquistata");
                    message("\033[0;36m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "\033[0m");
                }
                //STATION
            } else if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof Stations) {
                Stations stations = (Stations) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                message("\033[0;36m" + "Posizione attuale: " + gameManager.getCity(gameManager.getCurrentPlayer().getPosition()).getNome().replace("\n", " ") + " -> Prezzo: " + stations.getPrice() + "$" + "\033[0m");
                if (stations.isOwned()) {
                    stations.getPaid(gameManager.getStationsOwned(gameManager.getCurrentPlayer()), gameManager.getCurrentPlayer());
                    messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " ha pagato per essere passato su una proprietà già acquistata");
                    message("\033[0;36m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "\033[0m");
                }
            } else {
                message("\033[0;36m" + "Posizione attuale: " + gameManager.getCity(gameManager.getCurrentPlayer().getPosition()).getNome().replace("\n", " ") + "\033[0m");

                //TREASURES
                if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof Treasures) {
                    Treasures treasures = (Treasures) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                    message("\033[0;32m" + gameManager.getCurrentPlayer().getName().toUpperCase() + " ha ottenuto un premio per essere passato sulla casella Tesori" + "\033[0m");
                    gameManager.extractTreasure(treasures.pickRandomIndex(), gameManager.getCurrentPlayer());
                    //PRISON
                } else if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof ToPrison) {
                    ToPrison toPrison = (ToPrison) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                    messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " è andato in prigione per essere passato sulla casella Vai in Prigione");
                    toPrison.toPrison(gameManager.getCurrentPlayer());
                    //TAXES
                } else if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof Taxes) {
                    Taxes taxes = (Taxes) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                    messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " ha pagato per essere passato sulla casella Tassa");
                    taxes.redeemTaxes(gameManager.getCurrentPlayer());
                } else if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof Chances) {
                    Chances chance = (Chances) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                    message("\033[0;32m" + gameManager.getCurrentPlayer().getName().toUpperCase() + " ha ottenuto un premio per essere passato sulla casella Probabilità" + "\033[0m");
                    gameManager.extractChance(chance.pickRandomIndex(), gameManager.getCurrentPlayer());
                }
            }
            message("\033[0;36m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "$" + "\033[0m");
        }

    }

    /**
     * Chiede all'utente l'azione se vuole terminare il turno, comprare una proprietà, vendere una proprietà o visualizzare le proprietà degli altri giocatori.
     */
    public boolean askChoose() {
        int selection = 5;
        boolean flag = false;

        if (gameManager.getCurrentPlayer().inPrison()) {
            //FINE TURNO
            for (int i = 0; i < 4; i++) {
                if (gameManager.getPlayer(i).isMyTurn()) {
                    gameManager.getPlayer(i).setMyTurn(false);
                    if (i == 3 || gameManager.getPlayer(i + 1).getName().isEmpty()) {
                        gameManager.getPlayer(0).setMyTurn(true);
                    } else {
                        gameManager.getPlayer(i + 1).setMyTurn(true);
                    } // logic to end the player's turn.
                    break;
                }
            }
        } else {
            do {
                message("\nQuale azione vuoi eseguire ?\n[0] Termina Turno\n[1] Compra Proprietà\n[2] Vendi Proprietà \n[3] Visualizza Proprietà Possedute da ogni giocatore \n[4] Visualizza Posizione di tutti i giocatori");
                messagePrint("\nSelezione -> ");
                try {
                    selection = Integer.parseInt(s.nextLine());
                    if (selection != 0 && selection != 1 && selection != 2 && selection != 3 && selection != 4) {
                        messageRed("\n Hai richiesto un'azione inesistente");
                    }
                } catch (NumberFormatException e) {
                    messageRed("Non hai inserto un numero");
                }
            } while (selection != 0 && selection != 1 && selection != 2 && selection != 3 && selection != 4);

            switch (selection) {
                case 0:
                    //SALDO NEGATIVO
                    do {
                        if (gameManager.getCurrentPlayer().getMoney() < 0) {
                            AlertManager.showError("Non puoi terminare il turno con il saldo in negativo");
                            return false;
                        } else {
                            flag = true;
                        }
                    } while (!flag);

                    //FINE TURNO
                    for (int i = 0; i < 4; i++) {
                        if (gameManager.getPlayer(i).isMyTurn()) {
                            gameManager.getPlayer(i).setMyTurn(false);
                            if (i == 3 || gameManager.getPlayer(i + 1).getName().isEmpty()) {
                                gameManager.getPlayer(0).setMyTurn(true);
                            } else {
                                gameManager.getPlayer(i + 1).setMyTurn(true);
                            } // logic to end the player's turn.
                            break;
                        }
                    }

                    break;
                case 1:

                    Box box = gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                    if (gameManager.buyPropety()) {

                        //SALVO LE PROPRIETA PER STAMPARLE
                        for (int i = 0; i < 4; i++) {
                            if (gameManager.getPlayer(i).isMyTurn()) {
                                switch (i) {
                                    case 0:
                                        posssedute1.add(box);
                                        break;
                                    case 1:
                                        posssedute2.add(box);
                                        break;
                                    case 2:
                                        posssedute3.add(box);
                                        break;
                                    case 3:
                                        posssedute4.add(box);
                                        break;
                                }
                            }
                        }
                    }

                    message("\033[0;33m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "\033[0m");
                    return false;

                case 2:
                    Box save = null;
                    flag = false;
                    boolean flag2 = false;
                    int count = 0;

                    do {
                        printArr();
                        message("\nQuale proprietà vuoi vendere? (Inserire 'no' per annullare la vendita) ");
                        messagePrint("Selezione -> ");

                        String choose = s.nextLine();

                        if (choose.toLowerCase().trim().equals("no")) {
                            return false;
                        } else {
                            //CONTROLLO ESISTENZA CITTA
                            for (Box c : gameManager.getCities()) {
                                if (c.getNome().replace("\n", " ").toLowerCase().trim().equals(choose.toLowerCase().trim())) {
                                    message("Se vendi questa città otterrai solamente la metà del prezzo d'acquisto, vuoi vendere lo stesso? (si/no)");
                                    messagePrint("Selezione -> ");
                                    choose = s.nextLine();

                                    if (choose.toLowerCase().trim().equals("si")) {
                                        save = c;
                                        flag2 = true;
                                        flag = true;
                                    } else {
                                        flag = true;
                                        flag2 = false;
                                    }
                                    count++;
                                }
                            }

                            if (count == 0) {
                                messageRed("La città che hai inserito non esiste");
                            }
                        }

                    } while (!flag);


                    if (gameManager.sellPropety(save) && flag2) {
                        //RIMUOVO LE PROPRIETA PER STAMPARLE
                        for (int i = 0; i < 4; i++) {
                            if (gameManager.getPlayer(i).isMyTurn()) {
                                switch (i) {
                                    case 0:
                                        posssedute1.remove(save);
                                        break;
                                    case 1:
                                        posssedute2.remove(save);
                                        break;
                                    case 2:
                                        posssedute3.remove(save);
                                        break;
                                    case 3:
                                        posssedute4.remove(save);
                                        break;
                                }
                            }
                        }
                    }
                    message("\033[0;33m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "\033[0m");
                    return false;

                case 3:
                    printAll();
                    return false;
                case 4:
                    printAllPosition();
                    return false;
            }
        }
        return true;
    }

    /**
     * Stampa un messaggio a scelta.
     *
     * @param text messaggio che viene stampato.
     */
    public static void message(String text) {
        System.out.println(text);
    }

    /**
     * Stampa un messaggio a scelta senza andare a capo.
     *
     * @param text messaggio che viene stampato.
     */
    public void messagePrint(String text) {
        System.out.print(text);
    }

    /**
     * Stampa un messaggio a scelta di colore rosso.
     *
     * @param text messaggio che viene stampato.
     */
    public static void messageRed(String text) {
        System.out.println("\033[0;31m" + text + "\033[0m" + "\n");
    }

    /**
     * Stampa le proprietà possedute del giocatore a cui spetta il turno
     */
    public void printArr() {
        for (int i = 0; i < 4; i++) {
            if (gameManager.getPlayer(i).isMyTurn()) {
                switch (i) {
                    case 0:
                        if (posssedute1.isEmpty()) {
                            message("\033[0;33m" + "Proprietà possedute: { nessuna }" + "\033[0m");
                        } else {
                            messagePrint("\033[0;33m" + "Proprietà possedute: { " + "\033[0m");
                            for (Box box : posssedute1) {
                                if (posssedute1.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            messagePrint("\033[0;33m" + " }" + "\033[0m");
                        }
                        break;
                    case 1:
                        if (posssedute2.isEmpty()) {
                            message("\033[0;33m" + "Proprietà possedute: { nessuna }" + "\033[0m");
                        } else {
                            messagePrint("\033[0;33m" + "Proprietà possedute: { " + "\033[0m");
                            for (Box box : posssedute2) {
                                if (posssedute2.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            messagePrint("\033[0;33m" + " }" + "\033[0m");
                        }
                        break;
                    case 2:
                        if (posssedute3.isEmpty()) {
                            message("\033[0;33m" + "Proprietà possedute: { nessuna }" + "\033[0m");
                        } else {
                            messagePrint("\033[0;33m" + "Proprietà possedute: { " + "\033[0m");
                            for (Box box : posssedute3) {
                                if (posssedute3.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            messagePrint("\033[0;33m" + " }" + "\033[0m");
                        }
                        break;
                    case 3:
                        if (posssedute4.isEmpty()) {
                            message("\033[0;33m" + "Proprietà possedute: { nessuna }" + "\033[0m");
                        } else {
                            messagePrint("\033[0;33m" + "Proprietà possedute: { " + "\033[0m");
                            for (Box box : posssedute4) {
                                if (posssedute4.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            messagePrint("\033[0;33m" + " }" + "\033[0m");
                        }
                        break;
                }
            }
        }
    }

    /**
     * Stampa le proprietà di tutti i giocatori dopo la scelta dell'operazione.
     */
    public void printAll() {
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (posssedute1.isEmpty()) {
                        message("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { nessuna }" + "\033[0m");
                    } else {
                        if (gameManager.getPlayer(i).getName().isEmpty()) {
                            break;
                        } else {
                            messagePrint("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { " + "\033[0m");
                            for (Box box : posssedute1) {
                                if (posssedute1.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            message("\033[0;33m" + " }" + "\033[0m");
                        }

                    }
                    break;
                case 1:
                    if (posssedute2.isEmpty() && !Objects.equals(gameManager.getPlayer(i).getName(), "")) {
                        message("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { nessuna }" + "\033[0m");
                    } else {
                        if (gameManager.getPlayer(i).getName().isEmpty()) {
                            break;
                        } else {
                            messagePrint("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { " + "\033[0m");
                            for (Box box : posssedute2) {
                                if (posssedute2.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            message("\033[0;33m" + " }" + "\033[0m");
                        }
                    }
                    break;
                case 2:
                    if (posssedute3.isEmpty() && !Objects.equals(gameManager.getPlayer(i).getName(), "")) {
                        message("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { nessuna }" + "\033[0m");
                    } else {
                        if (gameManager.getPlayer(i).getName().isEmpty()) {
                            break;
                        } else {
                            messagePrint("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { " + "\033[0m");
                            for (Box box : posssedute3) {
                                if (posssedute3.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            message("\033[0;33m" + " }" + "\033[0m");
                        }
                    }
                    break;
                case 3:
                    if (posssedute4.isEmpty() && !Objects.equals(gameManager.getPlayer(i).getName(), "")) {
                        message("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { nessuna }" + "\033[0m");
                    } else {
                        if (gameManager.getPlayer(i).getName().isEmpty()) {
                            break;
                        } else {
                            messagePrint("\033[0;33m" + gameManager.getPlayer(i).getName().toUpperCase() + " possiede: { " + "\033[0m");
                            for (Box box : posssedute4) {
                                if (posssedute4.size() == 1) {
                                    messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + "\033[0m");
                                } else {
                                    messagePrint("\033[0;33m" + "  " + box.getNome().replace("\n", " ") + "\033[0m");
                                }
                            }
                            message("\033[0;33m" + " }" + "\033[0m");
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Stampa la posizione di ogni giocatore.
     */
    public void printAllPosition() {
        for (int i = 0; i < 4; i++) {
            if (!gameManager.getPlayer(i).getName().isEmpty()) {
                message("\033[0;36m" + "  " + gameManager.getPlayer(i).getName().toUpperCase() + " si trova nella casella: " + gameManager.getCity(gameManager.getPlayer(i).getPosition()).getNome() + " e possiede un saldo di: " + gameManager.getPlayer(i).getMoney() + "$" + "\033[0m");
            }
        }
    }


    /**
     * Stampa la griglia del gioco.
     */
    public void printBoard() {
        String[] Yellow = new String[]{" - " + "\033[0;43m" + "Traona" + "\033[0m", " - " + "\033[0;43m" + "Andalo" + "\033[0m"};
        String[] Orange = new String[]{" - " + "\033[0;42m" + "Regoledo" + "\033[0m", " - " + "\033[0;42m" + "Talamona" + "\033[0m", " - " + "\033[0;42m" + "Morbegno" + "\033[0m"};
        String[] White = new String[]{" - " + "\033[0;47m" + "Ardenno" + "\033[0m", " - " + "\033[0;47m" + "Ardenno" + "\033[0m", " - " + "\033[0;47m" + "Berbenno" + "\033[0m",};
        String[] Pink = new String[]{" - " + "\033[0;45m" + "Castione" + "\033[0m", " - " + "\033[0;45m" + "Castiones" + "\033[0m"};
        String[] Purple = new String[]{" - " + "\033[0;35m" + "Piantedo" + "\033[0m", " - " + "\033[0;35m" + "Chiesa" + "\033[0m", " - " + "\033[0;35m" + "Piantedo" + "\033[0m"};
        String[] Green = new String[]{" - " + "\033[0;32m" + "San Giacomo" + "\033[0m", " - " + "\033[0;32m" + "Tirano" + "\033[0m"};
        String[] LightBlue = new String[]{" - " + "\033[0;36m" + "Livigno" + "\033[0m", " - " + "\033[0;36m" + "Sondalo" + "\033[0m", "Grosio"};
        String[] Blue = new String[]{" - " + "\033[0;37m" + "Trepalle" + "\033[0m"+ "\033[0m", "\033[0;37m" + "Bormio" + "\033[0m"};

        message("Via(+200$)" + Yellow[0] + " - " + "\033[0;44m" + "Probabilità" + "\033[0m" + Yellow[1] + " - Stazione Nord" + Orange[0] + Orange[1] + Orange[2] + " - " + "\033[0;41m" + "Prigione" + "\033[0m");
        message(" ");
        message(Blue[1] + "                                                                                     " + White[0]);
        message(" ");
        message("Tasse(-200$)" + "                                                                                 " + White[1]);
        message("");
        message(Blue[0] + "                                                                                       " + White[2]);
        message("");
        message("Stazione Ovest" + "                                                                                       " + " - Stazione Est");
        message("");
        message(LightBlue[0] + "                                                                                       " + Pink[0]);
        message("");
        message(LightBlue[1] + "                                                                                       " + "- Tesori");
        message("");
        message(LightBlue[2] + "                                                                                       " + Pink[1]);
        message("");
        message("Vai in prigione" + Green[0] + " - " + "\033[0;44m" + "Probabilità" + "\033[0m" + Green[1] + " - Stazione Sud" + Purple[0] + Purple[1] + Purple[2] + " - " + "\033[0;41m" + "Parcheggio gratuito" + "\033[0m");

    }

}
