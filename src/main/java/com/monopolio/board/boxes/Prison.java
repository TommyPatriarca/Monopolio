package com.monopolio.board.boxes;

import com.monopolio.board.Box;

/**
 * Rappresenta la casella di gioco "Prigione", nella quale il giocatore è obbligato a fermarsi per 3 turni di gioco, a meno che non si paghi o si provi a ottenere un risultato uguale tirando ntrambi i dadi.
 */
public class Prison implements Box {
    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Prigione";
    }
}
