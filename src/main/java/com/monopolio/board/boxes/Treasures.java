package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;


/**
 * Rappresenta la casella "Tesori" nella quale se un giocatore dovesse passare otterrebbe un premio.
 */
public class Treasures implements Box {
    private ArrayList<String> treasures = new ArrayList<>();

    public Treasures() {
        treasures.add("Ottieni 20 monete."); // 0
        treasures.add("Ottieni 50 monete"); // 1
        treasures.add("Ottieni 100 monete"); // 2
        treasures.add("Ottieni 150 monete"); // 3
        treasures.add("Ottieni 200 monete"); // 4
    }

    /**
     * @param index è l'indice che indica il numero di tesoro da assegnare.
     * @return il tesoro da assegnare.
     */
    public String pick(int index) {
        return treasures.get(index);
    }

    /**
     * Estrae un tesoro  da assegnare in modo casuale.
     * @return
     */
    public String pickRandom() {
        int index = RandUtils.Integer(0, treasures.size()-1);
        return pick(index);
    }

    /**
     * @return il tesoro estratto in modo casuale tra dei numeri definiti.
     */
    public int pickRandomIndex() {
        return RandUtils.Integer(0, treasures.size()-1);
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Tesori";
    }
}
