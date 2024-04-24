package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;

public class Treasures implements Box {
    private ArrayList<String> treasures = new ArrayList<>();

    public Treasures() {
        treasures.add("(1) Something valuable.");
        treasures.add("(2) Something valuable.");
        treasures.add("(3) Something valuable.");
        treasures.add("(4) Something valuable.");
        treasures.add("(5) Something valuable.");
    }

    private String pick(int index) {
        return treasures.get(index);
    }

    private String pickRandom() {
        return treasures.get(RandUtils.Integer(0, treasures.size()));
    }

    @Override
    public String getNome() {
        return "Treasures";
    }
}
