package com.monopolio.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
public class RandUtils {
    private static final SecureRandom random = new SecureRandom();

    public static int Integer(int from, int to) {
        if (from >= to) {
            throw new IllegalArgumentException("to must be greater than from");
        }
        return random.nextInt((to - from) + 1) + from;
    }

    public static String String(Integer length){
        return new BigInteger(130, random).toString(24).substring(1, length);
    }

    public static String String(){
        return String(8);
    }

    public static Integer Integer(final Integer min, final Integer max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static Boolean Boolean(){
        return random.nextBoolean();
    }

    public static double[] coordinates(){
        return new double [] {40 + random.nextDouble(), (3 + random.nextDouble()) * -1 };
    }

    public static String email(){
        return RandUtils.String() + "@acme";
    }

    public static LocalDate birthday(){
        final int year = RandUtils.Integer(1950, 1995);
        final int month = RandUtils.Integer(1, 12);
        final int day = RandUtils.Integer(1, 25);
        return LocalDate.of(year, month, day);
    }

    public static LocalDateTime LocalDateTime() {
        final int month = RandUtils.Integer(1, 12);
        final int day = RandUtils.Integer(1, 25);
        final int hour = RandUtils.Integer(1, 23);
        final LocalDateTime rand = LocalDateTime.of(2014, month, day, hour, 0);
        return rand.isBefore(LocalDateTime.now()) ? rand : LocalDateTime();
    }

}