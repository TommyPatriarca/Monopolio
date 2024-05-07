package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

/**
 * Rappresenta la casella di gioco "Tassa", nella quale se il giocatore passa è costretto a versare 200 dollari.
 */
public class Taxes implements Box {
    private int amountDue;

    public Taxes(int amountDue) {
        this.amountDue = amountDue;
    }

    /**
     * Rimuove il denaro al giocatore che passa sulla casella.
     * @param player giocatore a cui vengono rimossi 200 dollari.
     */
    public void redeemTaxes(Player player) {
        player.removeMoney(amountDue);
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Tasse (-" + amountDue + "$)";
    }
}
