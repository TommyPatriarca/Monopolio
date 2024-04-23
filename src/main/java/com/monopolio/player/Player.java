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

    public int getSoldi(){
        return soldi;
    }

    public void sottraiSoldi(int soldi) {
        this.soldi = this.soldi-soldi;
    }

    public void aggiungiSoldi(int soldi) {
        this.soldi = this.soldi+soldi;
    }
}
