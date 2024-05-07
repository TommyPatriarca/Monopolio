package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

/**
 * Costringe il movimento del giocatore nella casella "Prigione" dopo esser passato su questa.
 */
public class ToPrison implements Box {

    /**
     * Sposta il giocatore nella casella 9 ("Prigione") e modifica lo stato dell' attributo "InPrison" da false a true.
     * @param player
     */
    public void toPrison(Player player) {
        player.setPosizione(9);
        player.setInPrison(true);
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "  Vai in\nPrigione";
    }
}
