package com.monopolio.cli;

import com.monopolio.Monopolio;
import com.monopolio.board.Box;
import com.monopolio.board.boxes.City;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;

import java.util.Scanner;

/**
 * Classe che permette l'interazione tramite linea di comando con l'utente.
 */
public class Cli {
    private Scanner s = new Scanner(System.in);
    private GameManager gameManager = new GameManager();
    private Controllore controllore;

    public Cli(Controllore controllore) {
        this.controllore = controllore;
    }

    /**
     * Chiede con quale tipologia di interfaccia si vuole utilizzare il gioco.
     *
     * @return il nbumero intero corrispondente alla scelta.
     */
    public int askInterface() {
        int selection = 0;
        do {
            message("Benvenuto!\nCon che tipo di interfaccia vuoi giocare?\n[1] CLI\n[2] GUI");
            message("\nSelezione -> ");
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
    public void start() {
        message("\n------ MONOPOLIO ------\n\n");
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
            message("Quanti giocatori vogliono giocare? ( mininimo -> 2 massimo -> 4)");
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
                    message("Inserisci il nome del giocatore 1");
                    String n1 = s.nextLine();
                    Player player1 = new Player(n1);
                    players[0] = player1;

                    message("Inserisci il nome del giocatore 2");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    Player player3 = new Player("");
                    players[2] = player3;
                    Player player4 = new Player("");
                    players[3] = player4;

                    flag2 = controllore.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        message(gameManager.getPlayer(0).getName());

                        gameManager.setPlayer(1, player2);
                        message(gameManager.getPlayer(1).getName());

                        gameManager.setPlayer(2, player3);
                        message(gameManager.getPlayer(2).getName());

                        gameManager.setPlayer(3, player4);
                        message(gameManager.getPlayer(3).getName());

                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;

            case 3:
                flag = true;
                do {
                    message("Inserisci il nome del giocatore 1");
                    String n1 = s.nextLine();
                    Player player1 = new Player(n1);
                    players[0] = player1;

                    message("Inserisci il nome del giocatore 2");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    message("Inserisci il nome del giocatore 2");
                    String n3 = s.nextLine();
                    Player player3 = new Player(n3);
                    players[2] = player3;

                    Player player4 = new Player("");
                    players[3] = player4;

                    flag2 = controllore.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        message(gameManager.getPlayer(0).getName());

                        gameManager.setPlayer(1, player2);
                        message(gameManager.getPlayer(1).getName());

                        gameManager.setPlayer(2, player3);
                        message(gameManager.getPlayer(2).getName());

                        gameManager.setPlayer(3, player4);
                        message(gameManager.getPlayer(3).getName());

                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;

            case 4:
                flag = true;
                do {
                    message("Inserisci il nome del giocatore 1");
                    String n1 = s.nextLine();
                    Player player1 = new Player(n1);
                    players[0] = player1;

                    message("Inserisci il nome del giocatore 2");
                    String n2 = s.nextLine();
                    Player player2 = new Player(n2);
                    players[1] = player2;

                    message("Inserisci il nome del giocatore 2");
                    String n3 = s.nextLine();
                    Player player3 = new Player(n3);
                    players[2] = player3;

                    message("Inserisci il nome del giocatore 2");
                    String n4 = s.nextLine();
                    Player player4 = new Player(n4);
                    players[3] = player4;

                    flag2 = controllore.duplicateNames(players);

                    //SE I NOMI SONO DUPLICATI
                    if (flag2 == false) {
                        gameManager.setPlayer(0, player1);
                        message(gameManager.getPlayer(0).getName());

                        gameManager.setPlayer(1, player2);
                        message(gameManager.getPlayer(1).getName());

                        gameManager.setPlayer(2, player3);
                        message(gameManager.getPlayer(2).getName());

                        gameManager.setPlayer(3, player4);
                        message(gameManager.getPlayer(3).getName());

                        flag = false;
                    } else {
                        messageRed("Non puoi inserire un nome uguale ad un altro");
                    }

                } while (flag);
                break;
        }
    }

    /**
     * Stampa un messaggio a scelta.
     *
     * @param text messaggio che viene stampato.
     */
    public static void message(String text) {
        System.out.println(text);
    }

    public void messagePrint(String text) {
        System.out.print(text);
    }

    /**
     * Stampa un messaggio a scelta di colore rosso.
     *
     * @param text messaggio che viene stampato.
     */
    public static void messageRed(String text) {
        System.out.println("\033[0;31m" + text + "\033[0m");
    }


    /**
     * Inizializza la griglia delle città inserendo le città nelle varie posizioni.
     */
    private void initBoard() {
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
     * Stampa i nomi delle città a schermo.
     */
    private void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 8 || j == 0 || j == 8) {
                    if (i == 0) {
                        messagePrint(gameManager.getCity(j).getNome().replace("\n", " ") + "   ");
                    } else if (i == 8) {
                        messagePrint(gameManager.getCity(24 - j).getNome().replace("\n", "     ") + "   ");
                    } else if (j == 0) {
                        System.out.println();
                        messagePrint(gameManager.getCity(32 - i).getNome().replace("\n", " ") + "                                                                                        ");
                    } else {
                        messagePrint(gameManager.getCity(8 + i).getNome().replace("\n", " ") + "   \n\n\n");
                    }

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
        while(true){
            askDice();
            flag = false;
            while(!flag){
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

        message("\033[0;32m" + "\n\nTURNO DI " + gameManager.getCurrentPlayer().getName().toUpperCase() + "\033[0m");
        do {
            message("\n\nInserire " + "si" + " per tirare i dadi");
            String choose = s.nextLine();
            if (choose.equals("si")) {
                d1 = controllore.throwDice();
                message("\nSelezione -> ");
                message("\033[0;36m" + " Dado 1 -> " + d1 + "\033[0m");
                d2 = controllore.throwDice();
                message("\nSelezione -> ");
                message("\033[0;36m" + " Dado 2 -> " + d2 + "\033[0m");
                flag = true;
            }
        } while (!flag);

        gameManager.getCurrentPlayer().moveForward(d1 + d2);
        //message("" + gameManager.getCurrentPlayer().getPosition());
    }

    /**
     * Chiede all'utente l'azione se vuole terminare il turno, comprare una proprietà o vendere una proprietà.
     */
    public boolean askChoose() {
        int selection = 0;
        boolean flag = false;
        do {
            message("\nQuale azione vuoi eseguire ?\n[0] Termina Turno\n[1] Compra Proprietà\n[2] Vendi Proprietà");
            message("\nSelezione -> ");
            try {
                selection = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                messageRed("Non hai inserto un numero");
            }
        } while (selection != 0 && selection != 1 && selection != 2);

        switch (selection) {
            case 0:
                //SALDO NEGATIVO
                do{
                    if(gameManager.getCurrentPlayer().getMoney() < 0) {
                        AlertManager.showError("Non puoi terminare il turno con il saldo in negativo");
                        return false;
                    }else{
                        flag = true;
                    }
                }while(!flag);

                //FINE TURNO
                for(int i=0; i<4; i++) {
                    if(gameManager.getPlayer(i).isMyTurn()) {
                        gameManager.getPlayer(i).setMyTurn(false);
                        if(i==3 || gameManager.getPlayer(i+1).getName().isEmpty()) {
                            gameManager.getPlayer(0).setMyTurn(true);
                        } else {
                            gameManager.getPlayer(i+1).setMyTurn(true);
                        } // logic to end the player's turn.
                        break;
                    }
                }

                break;
            case 1:
                Box box = gameManager.getCity(gameManager.getCurrentPlayer().getPosition());
                gameManager.buyPropety();
                return false;

            case 2:
                Box save = null;
                flag = false;
                int count = 0;

                do{
                    message("Quale proprietà vuoi vendere?");
                    String choose = s.nextLine();

                    //CONTROLLO ESISTENZA CITTA
                    for(Box c : gameManager.getCities()){
                        if(c.getNome().toLowerCase().equals(choose.toLowerCase())){
                            save = c;
                            count++;
                            flag = true;
                        }
                    }

                    if(count == 0){
                        messageRed("La città che hai inserito non esiste");
                    }
                }while(!flag);
                
                gameManager.sellPropety(save);
                return false;
        }
        return true;
    }
}
