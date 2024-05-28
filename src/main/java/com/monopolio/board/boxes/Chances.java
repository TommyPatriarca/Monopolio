package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;


/**
 * Rappresenta la casella "Probabilità" che permette di ottenere bonus o malus in base ad una scelta casuale.
 */
public class Chances implements Box {
    private ArrayList<String> chances = new ArrayList<>();

    public Chances() {
        chances.add("Ottieni 100 monete."); // 0
        chances.add("Vai alla casella Partenza."); // 1
        chances.add("Vai in prigione."); // 2
        chances.add("Paga 50 monete di multa."); // 3
        chances.add("Avanza di tre caselle."); // 4
    }

    /**
     * Permette di assegnare la "Probabilità" in base ad un indice.
     * @param index è la posizione della "Probabilità" all'interno dell'ArrayList.
     * @return la "Probabilità"
     */
    public String pick(int index) {
        return chances.get(index);
    }

    /**
     * Restituisce la "Probabilità" in base ad un indice scelto in modo casuale.
     * @return la "Probabilità"
     */
    public String pickRandom() {
        int index = RandUtils.Integer(0, chances.size()-1);
        return pick(index);
    }

    /**
     * Restituisce un indicie casuale per ottenere una "Probabilitò"
     * @return l'indice della "Probabilità"
     */
    public int pickRandomIndex() {
        return RandUtils.Integer(0, chances.size()-1);
    }


    /**
     * @return il nome di questa casella.
     */
    @Override
    public String getNome() {
        return "Probabilità";
    }

    /**
     * @return l'array che contiene le probabilità
     */
    public ArrayList<String> getChances() {
        return chances;
    }
}
