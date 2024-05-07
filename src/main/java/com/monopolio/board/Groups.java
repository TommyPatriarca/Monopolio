package com.monopolio.board;

public enum Groups {
    RED("/images/groups/red.png"),
    YELLOW("/images/groups/yellow.png"),
    ORANGE("/images/groups/orange.png"),
    PINK("/images/groups/pink.png"),
    GREEN("/images/groups/green.png"),
    CYAN("/images/groups/cyan.png"),
    BLUE("/images/groups/blue.png");

    private final String path;

    private Groups(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}