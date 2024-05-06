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
    private String pick(int index) {
        return chances.get(index);
    }

    /**
     * Permette di assegnare la "Probabilità" in base ad un indice scelto in modo casuale.
     * @return la "Probabilità"
     */
    private String pickRandom(Player player) {
        int index = RandUtils.Integer(0, chances.size());
        extractChance(index, player);
        return pick(index);
    }

    @Override
    public String getNome() {
        return "Probabilità";
    }

    /**
     * Gestisce il comportamento associato ad una probabilità.
     */
    public void extractChance(int index, Player player) {
        //todo: implementations

        switch (index) {
            case 0:
                // Ottieni 100 monete.
                player.addMoney(100);
                break;
            case 1:
                // Vai alla casella Partenza.
                player.setPosizione(0);
                break;
            case 2:
                // Vai in prigione.
                player.setInPrison(true);
                break;
            case 3:
                // Paga 50 monete di multa.
                player.removeMoney(50);
                break;
            case 4:
                // Avanza di tre caselle.
                player.setPosizione(player.getPosizione()+3);
                break;
        }
    }
}
