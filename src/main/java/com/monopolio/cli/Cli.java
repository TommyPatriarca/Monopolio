package com.monopolio.cli;

import com.monopolio.Monopolio;
import com.monopolio.board.Box;
import com.monopolio.board.boxes.*;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;

import java.util.ArrayList;
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
     * Stampa a schermo il titolo del programma e successivamente chiama le funzioni per inizializzare e stampare la tabella.
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

                    messagePrint("\nInserisci il nome del giocatore 2");
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

                    messagePrint("\nInserisci il nome del giocatore 2");
                    messagePrint("\nSelezione -> ");
                    String n3 = s.nextLine();
                    Player player3 = new Player(n3);
                    players[2] = player3;

                    messagePrint("\nInserisci il nome del giocatore 2");
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
     * Chiede all'utente di lanciare i dadi.
     */
    public void askDice() {
        boolean flag = false;
        int d1 = 0, d2 = 0;

        message("\n\n-----------------------------------------------------------------------------");
        message("\033[0;32m" + "TURNO DI " + gameManager.getCurrentPlayer().getName().toUpperCase() + "\033[0m");
        do {
            message("\nInserire " + "si" + " per tirare i dadi");
            messagePrint("Selezione -> ");
            String choose = s.nextLine();
            if (choose.equals("si")) {
                d1 = gameManager.throwDice();
                message("\033[0;36m" + "Dado 1 -> " + d1 + "\033[0m");
                d2 = gameManager.throwDice();
                message("\033[0;36m" + "Dado 2 -> " + d2 + "\033[0m");
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
                ToPrison toPrison = (ToPrison)  gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " è andato in prigione per essere passato sulla casella Vai in Prigione");
                toPrison.toPrison(gameManager.getCurrentPlayer());
            //TAXES
            }else if (gameManager.getCity(gameManager.getCurrentPlayer().getPosition()) instanceof Taxes){
                Taxes taxes = (Taxes) gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                messageRed(gameManager.getCurrentPlayer().getName().toUpperCase() + " ha pagato per essere passato sulla casella Tassa");
                taxes.redeemTaxes(gameManager.getCurrentPlayer());
            }
        }
        message("\033[0;36m" + "Saldo attuale: " + gameManager.getCurrentPlayer().getMoney() + "$" + "\033[0m");
    }

    /**
     * Chiede all'utente l'azione se vuole terminare il turno, comprare una proprietà o vendere una proprietà.
     */
    public boolean askChoose() {
        int selection = 4;
        boolean flag = false;

        do {

            message("\nQuale azione vuoi eseguire ?\n[0] Termina Turno\n[1] Compra Proprietà\n[2] Vendi Proprietà \n[3] Visualizza Proprietà Possedute");
            messagePrint("\nSelezione -> ");
            try {
                selection = Integer.parseInt(s.nextLine());
                if (selection != 0 && selection != 1 && selection != 2 && selection != 3) {
                    messageRed("\n Hai richiesto un'azione inesistente");
                }
            } catch (NumberFormatException e) {
                messageRed("Non hai inserto un numero");
            }
        } while (selection != 0 && selection != 1 && selection != 2 && selection != 3);

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
                int count = 0;

                do {
                    message("Quale proprietà vuoi vendere?");
                    messagePrint("Selezione -> ");
                    String choose = s.nextLine();

                    //CONTROLLO ESISTENZA CITTA
                    for (Box c : gameManager.getCities()) {
                        if (c.getNome().replace("\n", " ").toLowerCase().equals(choose.toLowerCase())) {
                            save = c;
                            count++;
                            flag = true;
                        }
                    }

                    if (count == 0) {
                        messageRed("La città che hai inserito non esiste");
                    }
                } while (!flag);

                if (gameManager.sellPropety(save)) {
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
                printArr();
                return false;
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

    //Todo: manca il continuo degli altri player
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
                                messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + " - " + "\033[0m");
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
                                messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + " - " + "\033[0m");
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
                                messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + " - " + "\033[0m");
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
                                messagePrint("\033[0;33m" + box.getNome().replace("\n", " ") + " - " + "\033[0m");
                            }
                            messagePrint("\033[0;33m" + " }" + "\033[0m");
                        }
                        break;
                }
            }
        }
    }

    public void printBoard() {
        String[] Yellow = new String[]{" - " + "\033[0;43m" + "Traona" + "\033[0m", " - " + "\033[0;43m" + "Andalo" + "\033[0m"};
        String[] Orange = new String[]{" - " + "\033[0;42m" + "Regoledo" + "\033[0m", " - " + "\033[0;42m" + "Talamona" + "\033[0m", " - " + "\033[0;42m" + "Morbegno" + "\033[0m"};
        String[] White = new String[]{" - " + "\033[0;47m" + "Ardenno" + "\033[0m", " - " + "\033[0;47m" + "Ardenno" + "\033[0m", " - " + "\033[0;47m" + "Berbenno" + "\033[0m",};
        String[] Pink = new String[]{"Castione", "Castiones"};
        String[] Purple = new String[]{"Sondrio", "Chiesa", "Piantedo"};
        String[] Green = new String[]{"San Giacomo", "Tirano"};
        String[] LightBlue = new String[]{"Livigno", "Sondalo", "Grosio"};
        String[] Blue = new String[]{"\033[0;44m" + "Trepalle" + "\033[0m", "\033[0;44m" + "Bormio" + "\033[0m"};

        message("Via(+200$)" + Yellow[0] + " - " + "\033[0;44m" + "Probabilità" + "\033[0m" + Yellow[1] + " - Stazione Nord" + Orange[0] + Orange[1] + Orange[2] + " - " + "\033[0;41m" + "Prigione" + "\033[0m");
        message(" ");
        message(Blue[1] + "                                                                                     " + White[0]);
        message(" ");
        message("Tasse(-200$)" + "                                                                                 " + White[1]);
        message("");
        message(Blue[0] + "                                                                                       " + White[2]);

    }

}
