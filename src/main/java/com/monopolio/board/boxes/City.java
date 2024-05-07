package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.board.Groups;
import com.monopolio.player.Player;

/**
 * La classe rappresenta una città all'interno del gioco su cui il giocatore può passare.
 */
public class City implements Box {
    private final Groups group;
    private final String name;
    private final int price;
    private final int houseprice;
    private final int hotelprice;
    private  int houseNumber=0; //un albergo corrisponde a 5 case
    private int payment=0;   //viene calcolato dopo
    private int basePayment;
    private Player owner;    //inizialmente non è di nessuno

    public City(Groups group, String name, int price, int houseprice, int hotelprice, int basePayment) {
        this.group = group;
        this.name = name;
        this.price = price;
        this.houseprice = houseprice;
        this.hotelprice = hotelprice;
        this.basePayment = basePayment;
    }

    /**
     * Una funzione di questo tipo permette al giocatore di comprare un "Hotel" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare l'hotel.
     */
    public void buyHotel(Player player){
        if(houseNumber==4 && player.soldi>=hotelprice){
            //puoi comprare l'albergo
            player.soldi-=hotelprice;
            houseNumber++;
            payment=basePayment*houseNumber;//TODO sostituire logica prezzi
        }
        else{
            //non puoi comprare
        }
    }

    /**
     * Permette al giocatore di comprare una "Casa" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare la casa.
     */
    public void buyHouse(Player player){
        if(player.soldi>=houseprice){
            //puoi comprare una casa
            player.soldi-=houseprice;
            houseNumber++;
            payment=basePayment*houseNumber;//TODO sostituire logica prezzi
        }
        else{
            //non puoi comprare
        }
    }

    /**
     * Permette al giocatore di comprare questa "Proprietà" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare questa proprietà.
     */
    public void buyPropriety(Player player){
        if(player.soldi>=price){
            owner=player;
        }
        else{
            //non puoi comprare
        }
    }

    /**
     * Permette al giocatore di vendere un "Hotel" solo nel caso in cui il giocatore lo possieda.
     * @param player è il giocatore che vuole vendere un hotel.
     */
    public void sellHotel(Player player){
        if(player.equals(owner) && houseNumber==5){
            //puoi vendere un albergo
            player.soldi+=hotelprice/2;
            houseNumber--;
            payment=basePayment*houseNumber;//TODO sostituire logica prezzi
        }
        else{
            //non puoi vendere
        }
    }

    /**
     * Permette al giocatore di vendere una "Casa" solo nel caso in cui il giocatore la possieda.
     * @param player è il giocatore che vuole vendere una casa.
     */
    public void sellHouse(Player player){
        if(player.equals(owner) && houseNumber>0 && houseNumber < 5){
            //puoi vendere una casa
            player.soldi+=houseprice/2;
            houseNumber--;
            payment=basePayment*houseNumber;//TODO sostituire logica prezzi
        }
        else{
            //non puoi vendere
        }
    }

    /**
     * Permette al giocatore di vendere questa "Proprietà" solo nel caso in cui il giocatore la possieda.
     * @param player è il giocatore che vuole vendere questa proprietà.
     */
    public void sellPropriety(Player player){
        if(player.equals(owner) && houseNumber==0){
            //puoi vendere una terreno
            //TODO non so come sistemare
            owner = null;
        }
        else{
            //non puoi vendere
        }
    }

    /**
     * Serve per visualizzare il nome della città.
     * @return il nome della città.
     */
    @Override
    public String getNome() {
        return name;
    }

    public Groups getGroup() {
        return group;
    }
}
