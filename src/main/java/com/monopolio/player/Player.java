package com.monopolio.player;

/**
 * Rappresenta il giocatore all' interno del gioco.
 */
public class Player {
    private final String name;
    public int money = 1500;
    private int position = 0;
    private boolean inPrison = false;
    private boolean myTurn = false;

    public Player(String name) {
        this.name = name;
    }

    /**
     * @return il nome del giocatore.
     */
    public String getName() {
        return name;
    }

    /**
     * @return il denaro che dispone il giocatore.
     */
    public int getMoney(){
        return money;
    }

    /**
     * Toglie del denaro al giocatore.
     * @param soldi denaro che viene tolto al giocatore.
     */
    public void removeMoney(int soldi) {
        this.money = this.money -soldi;
    }

    /**
     * Aggiunge del denaro al giocatore.
     * @param soldi denaro che viene aggiunto al giocatore.
     */
    public void addMoney(int soldi) {
        this.money = this.money +soldi;
    }

    /**
     * @return la posizione in cui si trova il giocatore nella griglia.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Posiziona il giocatore in una casella della griglia.
     * @param position in cui viene posizionato il giocatore.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Sposta il giocatore di tot posizioni in avanti.
     * @param position spostamento del giocatore.
     */
    public void moveForward(int position) {
        if(this.position+position>31) {
            this.position -= 31;
        }
        this.position += position;
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

    /**
     * Imposta se è il turno del player
     * @param myTurn boolean che viene assegnato a myTurn della classe
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * Verifica se è il turno del player
     * @return "true" se è il turno del player attuale altrimenti ritorna "false"
     */
    public boolean isMyTurn() {
        return myTurn;
    }
}
