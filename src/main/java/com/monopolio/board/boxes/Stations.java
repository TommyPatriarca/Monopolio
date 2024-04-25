package com.monopolio.board.boxes;

import com.monopolio.board.Box;

public class Stations implements Box {
    StationTypes type;

    public Stations(StationTypes type) {
        this.type = type;
    }

    @Override
    public String getNome() {
        return "Stazione "+type.getLabel();
    }

    public enum StationTypes {
        NORD("Nord"), EST("Est"), OVEST("Ovest"), SUD("Sud");

        private final String label;
        private StationTypes(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}