package com.monopolio.cli;

import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;
import com.monopolio.utils.RandUtils;

/**
 * DA FARE
 */
public class Controllore {
    private GameManager gameManager = new GameManager();

    public int throwDice() {
        return RandUtils.Integer(1, 4);
    }

    public boolean duplicateNames(Player[] players) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (players[i].getName().equals(players[j].getName()) && i != j && !(players[i].getName().isEmpty() || players[j].getName().isEmpty())) {
                    return true;
                }
            }
        }

        return false;
    }




}
