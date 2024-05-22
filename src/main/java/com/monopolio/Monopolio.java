package com.monopolio;

import com.monopolio.cli.Cli;
import com.monopolio.cli.Controllore;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.ui.Selection;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Rappresenta il punto di partenza del gioco che avvia il programma e la grafica.
 */
public class Monopolio extends Application {
    private static InterfaceManager.InterfaceType interfaceType;
    private static boolean devMode = false;
    private Controllore controllore = new Controllore();
    private Cli cli = new Cli(controllore);

    /**
     * Crea un oggetto di tipo "SceneManager" e permette di vedere lo schermo.
     *
     * @param primaryStage è la schermata che viene aperta e viene passata nel costruttore dell'oggetto "SceneManager".
     */
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showModeScreen();
    }

    public static void main(String[] args) {
        Selection selection = new Selection();
        selection.start(args);
    }

    public static boolean isDevMode() {
        return devMode;
    }

    public static InterfaceManager.InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public static void setInterfaceType(InterfaceManager.InterfaceType interfaceType) {
        Monopolio.interfaceType = interfaceType;
    }

    public Cli getCli() {
        return cli;
    }
}
