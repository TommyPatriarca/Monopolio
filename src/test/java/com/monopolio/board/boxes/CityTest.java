package com.monopolio.board.boxes;

import com.monopolio.board.Groups;
import com.monopolio.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    City city = new City(Groups.YELLOW, "Traona", 60, 50, 200, 10);
    Player player = new Player("test_name");
    Player player2 = new Player("test_name2");

    @BeforeEach
    void init() {
        city = new City(Groups.YELLOW, "Traona", 60, 50, 200, 10);
        player = new Player("test_name");
        player2 = new Player("test_name2");
    }

    @Test
    void getHouseNumber() {
        assertEquals(city.getHouseNumber(),0);
        city.buyHouse(player);
        assertEquals(city.getHouseNumber(),1);
    }

    @Test
    void buyHouse() {
        int money = player.getMoney();
        city.buyHouse(player);
        assertEquals(money-city.getHousePrice(1), player.getMoney());
    }

    @Test
    void getHousePrice() {
        assertEquals(city.getHousePrice(1),20);
        assertEquals(city.getHousePrice(2),30);
    }

    @Test
    void buyPropriety() {
        int money = player.getMoney();
        city.buyPropriety(player);
        assertEquals(money-city.getPrice(), player.getMoney());
    }

    @Test
    void sellHouse() {
        city.buyHouse(player);
        int money = player.getMoney();
        city.sellHouse(player);
        assertEquals(money + (city.getHousePrice(1)/2) ,player.getMoney());
    }

    @Test
    void sellPropriety() {
        city.buyPropriety(player);
        int money = player.getMoney();
        city.sellPropriety(player);
        assertEquals(money + (city.getPrice()/2),player.getMoney());
    }

    @Test
    void getNome() {
        assertEquals(player.getName(),"test_name");
    }

    @Test
    void getGroup() {
        assertEquals(city.getGroup(),Groups.YELLOW);
    }

    @Test
    void getOwner() {
        assertNull(city.getOwner());
        city.buyPropriety(player);
        assertEquals(city.getOwner(),player);
    }

    @Test
    void getPrice() {
        assertEquals(city.getPrice(),60);
    }

    @Test
    void isOwned() {
        assertFalse(city.isOwned());
        city.buyPropriety(player);
        assertTrue(city.isOwned());
    }

    @Test
    void getPaid() {
        int money = player.getMoney();
        city.getPaid(player);
        assertEquals(money-city.getPayment(city.getHouseNumber()), player.getMoney());
    }
}