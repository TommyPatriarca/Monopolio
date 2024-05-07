package com.monopolio.player;

/**
 * Rappresenta il giocatore all' interno del gioco.
 */
public class Player {
    public final String nome;
    public int soldi = 1500;
    private int posizione = 0;
    private boolean inPrison = false;

    public Player(String nome) {
        this.nome = nome;
    }

    /**
     * @return il nome del giocatore.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return il denaro che dispone il giocatore.
     */
    public int getMoney(){
        return soldi;
    }

    /**
     * Toglie del denaro al giocatore.
     * @param soldi denaro che viene tolto al giocatore.
     */
    public void removeMoney(int soldi) {
        this.soldi = this.soldi-soldi;
    }

    /**
     * Aggiunge del denaro al giocatore.
     * @param soldi denaro che viene aggiunto al giocatore.
     */
    public void addMoney(int soldi) {
        this.soldi = this.soldi+soldi;
    }

    /**
     * @return la posizione in cui si trova il giocatore nella griglia.
     */
    public int getPosizione() {
        return posizione;
    }

    /**
     * Posiziona il giocatore in una casella della griglia.
     * @param posizione in cui viene posizionato il giocatore.
     */
    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    /**
     * Verifica se il giocatore in quel momento si trova in prigione.
     * @return "true" se si trova in prigione, sennò "false".
     */
    public boolean inPrison() {
        return inPrison;
    }

    /**
     * Posiziona il giocatore in prigione modificando l'attributo che lo specifica("boolean inPrison").
     * @param inPrison specifica se il giocatore si trova in prigione oppure no.
     */
    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }


}
