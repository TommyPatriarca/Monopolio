package com.monopolio.board.boxes;

import com.monopolio.board.Board;

import java.util.ArrayList;

public class Chances implements Board {
    private ArrayList<String> chances;

    public Chances() {
        chances.add("(1) Some chance.");
        chances.add("(2) Some chance.");
        chances.add("(3) Some chance.");
        chances.add("(4) Some chance.");
        chances.add("(5) Some chance.");
    }

    @Override
    public String getNome() {
        return "Probabilità";
    }
}
