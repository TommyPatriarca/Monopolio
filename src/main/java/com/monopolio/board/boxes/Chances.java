package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;


/**
 * Rappresenta la casella "Probabilità" che permette di ottenere bonus o malus in base ad una scelta casuale.
 */
public class Chances implements Box {
    private ArrayList<String> chances = new ArrayList<>();

    public Chances() {
        chances.add("(1) Some chance.");
        chances.add("(2) Some chance.");
        chances.add("(3) Some chance.");
        chances.add("(4) Some chance.");
        chances.add("(5) Some chance.");
    }

    /**
     * Permette di assegnare la "Probabilità" in base ad un indice.
     * @param index è la posizione della "Probabilità" all'interno dell'ArrayList.
     * @return la "Probabilità"
     */
    private String pick(int index) {
        return chances.get(index);
    }

    /**
     * Permette di assegnare la "Probabilità" in base ad un indice scelto in modo casuale.
     * @return la "Probabilità"
     */
    private String pickRandom() {
        return chances.get(RandUtils.Integer(0, chances.size()));
    }

    @Override
    public String getNome() {
        return "Probabilità";
    }
}
