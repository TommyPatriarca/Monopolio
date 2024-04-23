package com.monopolio.player;

public class Player {
    public final String nome;
    public int soldi = 1500;

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
}
