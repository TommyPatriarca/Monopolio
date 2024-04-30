package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

public class StartBox implements Box {
    private int startMoney;

    public StartBox(int startMoney) {
        this.startMoney = startMoney;
    }

    public void redeemStart(Player player) {
        player.addMoney(startMoney);
    }

    @Override
    public String getNome() {
        return "      VIA\nritirare 200€";
    }
}
