package com.monopolio;

import com.monopolio.cli.Cli;
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
    private Cli cli = new Cli();

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

    /**
     * Accesso principale all'avvio del programmma.
     * @param args array di stringhe che rappresenta gli argomenti passati al programma dalla riga di comando quando viene avviato.
     */
    public static void main(String[] args) {
        Selection selection = new Selection();
        selection.start(args);
    }

    /**
     * @return com'è stata avviata l'applicazione ("true" se in modalità sviluppatore, "false" se in modalità normale).
     */
    public static boolean isDevMode() {
        return devMode;
    }

    /**
     * @return il tipo di interfaccia.
     */
    public static InterfaceManager.InterfaceType getInterfaceType() {
        return interfaceType;
    }

    /**
     * Imposta il tipo d'interfaccia.
     * @param interfaceType rappresenta il tipo d'interfaccia.
     */
    public static void setInterfaceType(InterfaceManager.InterfaceType interfaceType) {
        Monopolio.interfaceType = interfaceType;
    }

    /**
     * @return la Cli.
     */
    public Cli getCli() {
        return cli;
    }
}
