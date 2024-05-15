package com.monopolio.cli;

import com.monopolio.utils.RandUtils;

/**
 * DA FARE
 */
public class Controllore {

    public int throwDice(){
        return RandUtils.Integer(1,4);
    }

    public boolean duplicateNames(){
        return false;   // da modificare
    }
}
