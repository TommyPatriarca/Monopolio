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
    private Player owner = null;    //inizialmente non è di nessuno

    public City(Groups group, String name, int price, int houseprice, int hotelprice, int basePayment) {
        this.group = group;
        this.name = name;
        this.price = price;
        this.houseprice = houseprice;
        this.hotelprice = hotelprice;
        this.basePayment = basePayment;
        this.payment = basePayment;
    }

    /**
     * @return il numero delle case posizionate in quella città
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Permette al giocatore di comprare una "Casa" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare la casa.
     */
    public void buyHouse(Player player){
        if(player.money >=getHousePrice(houseNumber+1)){
            //puoi comprare una casa
            player.money -=getHousePrice(houseNumber+1);
            houseNumber++;
            payment=getPayment(houseNumber);//TODO sostituire logica prezzi
        }
        else{
            //non puoi comprare
        }
    }


    /**
     * @param houseNumber è il numero delle case posizionate in questa città.
     * @return il prezzo della case che vuoi comprare.
     */
    public int getHousePrice(int houseNumber){
        return basePayment*(houseNumber+1);//TODO sostituire logica prezzi
    }

    /**
     * Permette al giocatore di comprare questa "Proprietà" solo dopo aver verificato la sua disponibilità economica.
     * @param player è il giocatore che vuole comprare questa proprietà.
     */
    public void buyPropriety(Player player){
        if(player.money >= price){
            owner=player;
            player.removeMoney(price);
        }
        else{
            //non puoi comprare
        }
    }

    /**
     * Permette al giocatore di vendere una "Casa" solo nel caso in cui il giocatore la possieda.
     * @param player è il giocatore che vuole vendere una casa.
     */
    public void sellHouse(Player player){
        if(player.equals(owner) && houseNumber > 0 && houseNumber <= 5){
            //puoi vendere una casa
            player.money += getHousePrice(houseNumber)/2;
            houseNumber--;
            payment=basePayment/(houseNumber+1);//TODO sostituire logica prezzi
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
            player.addMoney(price/2);
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

    /**
     * @return il gruppo di appartenenza di questa città
     */
    public Groups getGroup() {
        return group;
    }

    /**
     * @return il player che possiede questa città
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return il prezzo di questa città
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return "true" se la città è posseduta da qualcuno, sennò "false".
     */
    public boolean isOwned() {
        return owner != null;
    }

    /**
     * @param houseNumber è il numero delle case posozionate.
     * @return il denaro che deve essere assegnato al proprietario della città.
     */
    public int getPayment(int houseNumber) {
        return basePayment*(houseNumber+1);
    }

    /**
     * @param player è il giocatore a cui vanno tolti i soldi per essere passato sulla città.
     */
    public void getPaid(Player player) {
        player.removeMoney(getPayment(houseNumber));
    }
}
