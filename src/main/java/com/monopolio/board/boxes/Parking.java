package com.monopolio.board.boxes;

import com.monopolio.board.Box;

/**
 * Rappresenta la casella di gioco "Parcheggio Gratutito", nella quale il giocatore passa liberamente senza dover fare nulla .
 */
public class Parking implements Box {
    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Percheggio\n  Gratuito";
    }
}
