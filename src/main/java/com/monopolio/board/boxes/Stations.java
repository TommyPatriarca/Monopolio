package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.board.Groups;
import com.monopolio.player.Player;


/**
 * Rappresenta la casella del gioco "Stazione", presente 4 volte nella griglia (NORD, SUD, EST, OVEST);
 * Più stazioni possiede un giocatore, più costa comprarla.
 */
public class Stations implements Box {
    private StationTypes type;
    private Player owner = null;    //inizialmente non è di nessuno
    private final int price;
    private int basePayment;

    public Stations(StationTypes type, int price, int basePaymet) {
        this.type = type;
        this.price = price;
        this.basePayment = basePayment;
    }

    /**
     * @return il nome della casella.
     */
    @Override
    public String getNome() {
        return "Stazione\n"+type.getLabel();
    }

    /**
     * Permette al giocatore di comprare questa "Proprietà" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare questa proprietà.
     */
    public void buyStation(Player player){
        if(player.money >= price){
            owner=player;
            player.removeMoney(price);
        }
        else{
            //non puoi comprare
        }
    }

    /**
     * Permette al giocatore di vendere questa "Proprietà" solo nel caso in cui il giocatore la possieda.
     * @param player è il giocatore che vuole vendere questa proprietà.
     */
    public void sellStation(Player player){
        if(player.equals(owner)){
            player.addMoney(price/2);
            owner = null;
        }
        else{
            //non puoi vendere
        }
    }

    public Player getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public boolean isOwned() {
        return owner != null;
    }

    public void getPaid(int stationsOwned, Player player) {
        player.removeMoney(basePayment*stationsOwned);
    }

    /**
     * Rappresenta i vari tipi di "Stazione" disponibili.
     */
    public enum StationTypes {
        NORD("Nord"), EST("Est"), OVEST("Ovest"), SUD("Sud");

        private final String label;
        private StationTypes(String label) {
            this.label = label;
        }

        /**
         * @return il label che contiene il tipo di "Stazione".
         */
        public String getLabel() {
            return label;
        }
    }

    public int getBasePayment() {
        return basePayment;
    }
}