package com.monopolio.board.boxes;

import com.monopolio.board.Box;
import com.monopolio.utils.RandUtils;

import java.util.ArrayList;
/**
 * DA FARE PIù AVANTI
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Treasures implements Box {
    private ArrayList<String> treasures = new ArrayList<>();

    public Treasures() {
        treasures.add("(1) Vai direttamente in prigione");
        treasures.add("(2) Esci gratis di prigione");
        treasures.add("(3) Avanza fino al Via");
        treasures.add("(4) Vai alla Stazione Sud");
        treasures.add("(5) Vai alla prigione");
        treasures.add("(6) Vai fino a Morbegno");
        treasures.add("(7) Paga 40€ per ogni casa e 115€ per ogni albergo");
        treasures.add("(8) Paga 10€");
        treasures.add("(9) Paga 50€");
        treasures.add("(10) Paga 150€");
        treasures.add("(11) Ricevi 10€");
        treasures.add("(12) Ricevi 100€");
        treasures.add("(13) Ricevi 150€");
        treasures.add("(14) Ricevi 200€");
        treasures.add("(15) Ricevi 20€");
        treasures.add("(16) Ricevi 50€");

    }

    private String pick(int index) {
        return treasures.get(index);
    }

    private String pickRandom() {
        return treasures.get(RandUtils.Integer(0, treasures.size()));
    }

    @Override
    public String getNome() {
        return "Treasures";
    }
}
