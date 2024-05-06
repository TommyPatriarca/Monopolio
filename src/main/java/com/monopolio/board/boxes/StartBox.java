package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

/**
 * Rappresenta la casella del gioco "Via", nella quale se il giocatore passa, riceve 200 dollari.
 */
public class StartBox implements Box {
    private int startMoney;

    public StartBox(int startMoney) {
        this.startMoney = startMoney;
    }

    /**
     * Aggiunge il denaro al giocatore che passa per la casella "Via".
     * @param player è il giocatore che passa per la casella.
     */
    public void redeemStart(Player player) {
        player.addMoney(startMoney);
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Via (+"+startMoney+"$)";
    }
}
