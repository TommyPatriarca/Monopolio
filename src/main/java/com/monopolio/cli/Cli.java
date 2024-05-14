package com.monopolio.cli;

import com.monopolio.board.Groups;
import com.monopolio.board.boxes.*;
import com.monopolio.managers.GameManager;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.Scanner;

public class Cli {
    private Scanner s = new Scanner(System.in);
    private GameManager gameManager = new GameManager();
    /**
     * Stampa un messaggio a scelta.
     * @param text messaggio che viene stampato.
     */
    public void message(String text){
        System.out.println(text);
    }

    public int askInterface(){
        int selection;
        do{
            System.out.println("Benvenuto!\nCon che tipo di interfaccia vuoi giocare?\n[1] CLI\n[2] GUI");
            System.out.print("\nSelezione -> ");
            selection = s.nextInt();
        } while(selection != 1 && selection != 2);

        return selection;
    }

    public void start(){
        System.out.println("\n------ MONOPOLIO ------\n\n");
        initBoard();
        printBoard();
    }

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

    private void printBoard() {
        for(int i=0;i < 9;i++){
            for(int j=0;j < 9;j++){
                if(i==1 && j == 0){ // todo: sistema la stampa
                    System.out.println(gameManager.getCity(31).getNome());
                }
            }
        }
    }
}
