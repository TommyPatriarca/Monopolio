package com.monopolio.board;

/**
 * Rappresenta i vari gruppi di città presenti nel gioco.
 */
public enum Groups {
    RED("/images/groups/red.png"),
    YELLOW("/images/groups/yellow.png"),
    ORANGE("/images/groups/orange.png"),
    PINK("/images/groups/pink.png"),
    PURPLE("/images/groups/purple.png"),
    GREEN("/images/groups/green.png"),
    CYAN("/images/groups/cyan.png"),
    BLUE("/images/groups/blue.png"),
    BLACK("/images/groups/black.png");

    private final String path;

    private Groups(String path) {
        this.path = path;
    }

    /**
     * @return il gruppo a cui appartiene una città.
     */
    public String getPath() {
        return path;
    }
}