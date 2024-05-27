package com.monopolio.board.boxes;

import com.monopolio.board.Groups;
import com.monopolio.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    City city = new City(Groups.YELLOW, "Traona", 60, 50, 200, 10);
    Player player = new Player("test_name");

    @Test
    void getHouseNumber() {
        assertEquals(city.getHouseNumber(),0);
        city.buyHouse(player);
        assertEquals(city.getHouseNumber(),1);
    }

    @Test
    void buyHouse() {
    }

    @Test
    void getHousePrice() {
    }

    @Test
    void buyPropriety() {
        Player playerUno = new Player("BuyHouse_TEST");
        int money = playerUno.getMoney();
        city.buyPropriety(playerUno);

        // Expected 1500-60, Actual 1440
        assertEquals(money-city.getPrice(), playerUno.getMoney());
    }

    @Test
    void sellHouse() {
    }

    @Test
    void sellPropriety() {
    }

    @Test
    void getNome() {
    }

    @Test
    void getGroup() {
    }

    @Test
    void getOwner() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void isOwned() {
    }

    @Test
    void getPayment() {
    }

    @Test
    void getPaid() {
    }
}