package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

public class Taxes implements Box {
    private int amountDue;

    public Taxes(int amountDue) {
        this.amountDue = amountDue;
    }

    public void redeemTaxes(Player player) {
        player.removeMoney(amountDue);
    }

    @Override
    public String getNome() {
        return "Tasse ($" + amountDue + ")";
    }
}
