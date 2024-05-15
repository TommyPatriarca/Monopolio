package com.monopolio.cli;

import com.monopolio.board.Groups;
import com.monopolio.board.boxes.*;
import com.monopolio.managers.GameManager;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.Scanner;

/**
 * Classe che permette l'interazione tramite linea di comando con l'utente.
 */
public class Cli {
    private Scanner s = new Scanner(System.in);
    private GameManager gameManager = new GameManager();
    private Controllore controllore;

    public Cli(Controllore controllore){
        this.controllore = controllore;
    }

    /**
     * Stampa un messaggio a scelta.
     * @param text messaggio che viene stampato.
     */
    public void message(String text){
        System.out.println(text);
    }

    /**
     * Chiede con quale tipologia di interfaccia si vuole utilizzare il gioco.
     * @return il nbumero intero corrispondente alla scelta.
     */
    public int askInterface(){
        int selection;
        do{
            System.out.println("Benvenuto!\nCon che tipo di interfaccia vuoi giocare?\n[1] CLI\n[2] GUI");
            System.out.print("\nSelezione -> ");
            selection = s.nextInt();
        } while(selection != 1 && selection != 2);

        return selection;
    }

    /**
     * Stampa a schermo il titolo del programma e successivamente chiama le funzioni per inizializzare e stampare la tabella.
     */
    public void start(){
        System.out.println("\n------ MONOPOLIO ------\n\n");
        initBoard();
        printBoard();
        handle();
    }

    /**
     * Chiama tutte le funzioni che permettono lo svolgimento del gioco.
     */
    public void handle(){
        askDice();

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
                    gameManager.initCity(number-1);
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
                        System.out.print(gameManager.getCity(j).getNome().replace("\n"," ")+"   ");
                    }else if (i == 8) {
                        System.out.print(gameManager.getCity(24-j).getNome().replace("\n","     ")+"   ");
                    }else if (j == 0){
                        System.out.println();
                        System.out.print(gameManager.getCity(32-i).getNome().replace("\n"," ")+"                                                                                        ");
                    }
                    else {
                        System.out.print(gameManager.getCity(8+i).getNome().replace("\n"," ")+"   \n\n\n");
                    }

                }
            }
        }
    }


    /**
     * Chiede all'utente di lanciare i dadi.
     */
    public void askDice(){
        boolean flag = false;
        do{
            message("\n\nPremere la barra spaziartrice per tirare i dadi");
            String choose = s.next();
                if (choose.equals("si")){
                    System.out.println("Dado 1 -> " + controllore.throwDice());
                    System.out.println("Dado 2 -> " +controllore.throwDice());
                    flag = true;
                }
        }while(flag);
    }

    
}
