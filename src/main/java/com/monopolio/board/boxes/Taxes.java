package com.monopolio.board.boxes;

import com.monopolio.board.Box;

public class Taxes implements Box {
    private int amountDue;

    public Taxes(int amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String getNome() {
        return "Tasse\n($" + amountDue + ")";
    }
}
