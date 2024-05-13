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
        int choose;
        do{
            System.out.println("Benvenuto!\nCon che tipo di interfaccia vuoi giocare? (1 --> CLI  2 --> GUI)");
            choose = s.nextInt();
        }while(choose != 1 && choose != 2);

        return choose;
    }

    public void start(){
        System.out.println("\n------MONOPOLIO VALTELLINESE------\n\n");

        for(int i = 0;i < 9;i++){
            for(int j=0;j < 9;j++){
                if(i==1 && j == 0){
                    createButton(31);
                    System.out.println(gameManager.getCity(31).getNome());
                }

            }
        }
    }

    private void createButton(int number) {
        switch (number) {
            case 0:
                gameManager.setCity(number, new StartBox(200));
                break;
            case 1:
                gameManager.setCity(number, new City(Groups.BLUE, "Traona", 60, 50, 200, 10));
                break;
            case 2:
                gameManager.setCity(number, new Chances());
                break;
            case 3:
                gameManager.setCity(number, new City(Groups.BLUE, "Andalo", 60, 50, 200, 10));
                break;
            case 4:
                gameManager.setCity(number, new Taxes(200));
                break;
            case 5:
                gameManager.setCity(number, new City(Groups.YELLOW, "Regoledo", 100, 50, 200, 10));
                break;
            case 6:
                gameManager.setCity(number, new Treasures());
                break;
            case 7:
                gameManager.setCity(number, new City(Groups.YELLOW, "Morbegno", 100, 50, 200, 10));
                break;
            case 8:
                gameManager.setCity(number, new Prison());
                break;
            case 9:
                gameManager.setCity(number, new City(Groups.YELLOW, "Talamona", 120, 50, 200, 10));
                break;
            case 10:
                gameManager.setCity(number, new City(Groups.ORANGE, "Ardenno", 140, 50, 200, 10));
                break;
            case 11:
                gameManager.setCity(number, new Stations(Stations.StationTypes.EST));
                break;
            case 12:
                gameManager.setCity(number, new City(Groups.ORANGE, "Berbenno", 140, 50, 200, 10));
                break;
            case 13:
                gameManager.setCity(number, new City(Groups.ORANGE, "Castione", 160, 50, 200, 10));
                break;
            case 14:
                gameManager.setCity(number, new City(Groups.PINK, "Castiones", 160, 50, 200, 10));
                break;
            case 15:
                gameManager.setCity(number, new Chances());
                break;
            case 16:
                gameManager.setCity(number, new Parking());
                break;
            case 17:
                gameManager.setCity(number, new City(Groups.PINK, "Sondrio", 180, 50, 200, 10));
                break;
            case 18:
                gameManager.setCity(number, new City(Groups.PINK, "Chiesa", 180, 50, 200, 10));
                break;
            case 19:
                gameManager.setCity(number, new City(Groups.GREEN, "Piantedo", 220, 50, 200, 10));
                break;
            case 20:
                gameManager.setCity(number, new Treasures());
                break;
            case 21:
                gameManager.setCity(number, new City(Groups.GREEN, "San Giacomo", 220, 50, 200, 10));
                break;
            case 22:
                gameManager.setCity(number, new City(Groups.GREEN, "Tresenda", 240, 50, 200, 10));
                break;
            case 23:
                gameManager.setCity(number, new City(Groups.CYAN, "Tirano", 260, 50, 200, 10));
                break;
            case 24:
                gameManager.setCity(number, new ToPrison());
                break;
            case 25:
                gameManager.setCity(number, new Stations(Stations.StationTypes.SUD));
                break;
            case 26:
                gameManager.setCity(number, new City(Groups.CYAN, "Sondalo", 280, 50, 200, 10));
                break;
            case 27:
                gameManager.setCity(number, new City(Groups.CYAN, "Grosio", 260, 50, 200, 10));
                break;
            case 28:
                gameManager.setCity(number, new City(Groups.RED, "Livigno", 300, 50, 200, 10));
                break;
            case 29:
                gameManager.setCity(number, new City(Groups.RED, "Trepalle", 300, 50, 200, 10));
                break;
            case 30:
                gameManager.setCity(number, new Chances());
                break;
            case 31:
                gameManager.setCity(number, new City(Groups.RED, "Bormio", 300, 50, 200, 10));
                break;

            default:
                System.out.println("Could not find city....");
        }
    }
}
