package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;


/**
 * Da fare
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

    public String pick(int index) {
        return treasures.get(index);
    }

    public String pickRandom() {
        int index = RandUtils.Integer(0, treasures.size()-1);
        return pick(index);
    }

    public int pickRandomIndex() {
        return RandUtils.Integer(0, treasures.size()-1);
    }


    @Override
    public String getNome() {
        return "Probabilità";
    }
}
