package com.monopolio.player;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerPawn extends Circle {
    private final Player player;

    public PlayerPawn(Player player, Color color) {
        super(5, color); // Adjust the radius as needed
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
