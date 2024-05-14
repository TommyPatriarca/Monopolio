package com.monopolio.board.boxes;

import com.monopolio.board.Box;


/**
 * Rappresenta la casella del gioco "Stazione", presente 4 volte nella griglia (NORD, SUD, EST, OVEST);
 * Più stazioni possiede un giocatore, più costa comprarla.
 */
public class Stations implements Box {
    StationTypes type;

    public Stations(StationTypes type) {
        this.type = type;
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Stazione\n"+type.getLabel();
    }

    /**
     * Rappresenta i vari tipi di "Stazione" disponibili.
     */
    public enum StationTypes {
        NORD("Nord"), EST("Est"), OVEST("Ovest"), SUD("Sud");

        private final String label;
        private StationTypes(String label) {
            this.label = label;
        }

        /**
         * @return il label che contiene il tipo di "Stazione".
         */
        public String getLabel() {
            return label;
        }
    }
}