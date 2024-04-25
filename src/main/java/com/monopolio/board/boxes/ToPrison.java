package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.player.Player;

public class ToPrison implements Box {

    public void toPrison(Player player) {
        player.setPosizione(9);
        player.setInPrison(true);
    }

    @Override
    public String getNome() {
        return "  Vai in\nPrigione";
    }
}
