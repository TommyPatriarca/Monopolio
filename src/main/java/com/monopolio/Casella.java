package com.monopolio;

public class Casella {
    public final String name;
    public final int price;
    public final int houseprice;
    public final int hotelprice;
    public  int houseNumber; //un albergo corrisponde a 5 case
    public int payment;
    public int basePayment;
    public Player owner;

    public Casella(String name, int price, int houseprice, int hotelprice, int houseNumber, int payment, int basePayment, Player owner) {
        this.name = name;
        this.price = price;
        this.houseprice = houseprice;
        this.hotelprice = hotelprice;
        this.houseNumber = houseNumber;
        this.payment = payment;
        this.owner = owner;
        this.basePayment = basePayment;
    }

    //permette di comprare un albergo
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

    //permette di comprare una casa
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

    //permette di comprare un terreno
    public void buyPropriety(Player player){
        if(player.soldi>=price){
            //puoi comprare una terreno
            owner=player;
        }
        else{
            //non puoi comprare
        }
    }

    //permette di vendere un albergo
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

    //permette di vendere una casa
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

    //permette di vendere un terreno
    public void sellPropriety(Player player){
        if(player.equals(owner) && houseNumber==0){
            //puoi vendere una terreno
            //TODO non so come sistemare
            //owner=NULL;
        }
        else{
            //non puoi vendere
        }
    }

}
