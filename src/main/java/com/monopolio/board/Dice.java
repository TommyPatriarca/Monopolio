package com.monopolio.board;

import com.monopolio.utils.RandUtils;

public class Dice {
    private Boolean status = false;
    private int value;

    public int roll(){
        status = true;
        value = RandUtils.Integer(1,6);
        return value;
    }

}
