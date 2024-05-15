package com.monopolio;

import com.monopolio.cli.Cli;
import com.monopolio.cli.Controllore;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.managers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Rappresenta il punto di partenza del gioco che avvia il programma e la grafica.
 */
public class Monopolio extends Application {
    private static InterfaceManager.InterfaceType interfaceType;
    private static boolean devMode = true;
    private static Controllore controllore = new Controllore();
    private static Cli cli = new Cli(controllore);
    private static Scanner s = new Scanner(System.in);
    /**
     * Crea un oggetto di tipo "SceneManager" e permette di vedere lo schermo.
     * @param primaryStage è la schermata che viene aperta e viene passata nel costruttore dell'oggetto "SceneManager".
     */
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showModeScreen();
    }

    public static void main(String[] args) {
        if(cli.askInterface() == 2){
            interfaceType = InterfaceManager.InterfaceType.GUI;
            launch(args);
        }else{
            interfaceType = InterfaceManager.InterfaceType.CLI;
            cli.start();
        }
    }

    public static boolean isDevMode() {
        return devMode;
    }

    public static InterfaceManager.InterfaceType getInterfaceType() {
        return interfaceType;
    }
}
