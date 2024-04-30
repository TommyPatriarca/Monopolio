package com.monopolio.player;

public class Player {
    public final String nome;
    public int soldi = 1500;
    private int posizione = 0;
    private boolean inPrison = false;

    public Player(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getMoney(){
        return soldi;
    }

    public void removeMoney(int soldi) {
        this.soldi = this.soldi-soldi;
    }

    public void addMoney(int soldi) {
        this.soldi = this.soldi+soldi;
    }

    public int getSoldi() {
        return soldi;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public boolean inPrison() {
        return inPrison;
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }


}
