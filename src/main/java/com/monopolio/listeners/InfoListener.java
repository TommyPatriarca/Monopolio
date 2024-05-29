package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.Box;
import com.monopolio.board.boxes.*;
import com.monopolio.cli.Cli;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Gestisce il click del bottone "Informazioni" e stampa le informazioni.
 */
public class InfoListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Game game;

    public InfoListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    /**
     * Dopo il click del bottone stampa tutte le informazioni.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        // print selected property info (AlertManager)
        if(game.getSelectedButton() != null) {
            Box box = gameManager.getCity(game.getSelectedButtonIndex());

            String message = "";
            if (box instanceof Chances) {
                //Chances chances = (Chances) box;
                //message = "Se ci vai sopra verrà selezionata un'attivita casuale da far fare al giocatore.";

                // City
            } else if (box instanceof City) {
                City city = (City) box;
                if(city.isOwned()) {
                    message += "Proprietario: " + city.getOwner().getName() +"\n";
                    message += "Pagamento: " + city.getPayment(city.getHouseNumber()) +"$\n";
                    message += "Case: " + city.getHouseNumber() +"\n";
                } else {
                    message += "Proprietario: nessuno\n";
                }
                message += "\nPrezzo 1 casa: " + city.getHousePrice(1) +"$\n";
                message += "Prezzo 2 case: " + city.getHousePrice(2) +"$\n";
                message += "Prezzo 3 case: " + city.getHousePrice(3) +"$\n";
                message += "Prezzo 4 case: " + city.getHousePrice(4) +"$\n";
                message += "Prezzo 5 case: " + city.getHousePrice(5) +"$\n";

                message += "\nPagamento 0 case: " + city.getPayment(0) +"$\n";
                message += "Pagamento 1 casa: " + city.getPayment(1) +"$\n";
                message += "Pagamento 2 case: " + city.getPayment(2) +"$\n";
                message += "Pagamento 3 case: " + city.getPayment(3) +"$\n";
                message += "Pagamento 4 case: " + city.getPayment(4) +"$\n";
                message += "Pagamento 5 case: " + city.getPayment(5) +"$\n";

                // Stations
            } else if (box instanceof Stations) {
                Stations stations = (Stations) box;
                if(stations.isOwned()) {
                    message += "Proprietario: " + stations.getOwner().getName() +"\n";
                } else {
                    message += "Proprietario: nessuno\n";
                }

                // Error
            } else {
                if (Monopolio.isDevMode()) {
                    System.out.println("Could not handle property info");
                }
            }

            AlertManager.show("Hai selezionato: "+box.getNome().replace("\n", " ")+"\n"+message);
        } else {
            AlertManager.showError("Non hai ancora selezionato nessuna proprietà");
        }
    }
}
