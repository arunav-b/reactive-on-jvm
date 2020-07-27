package com.arunav.learning.rxjava.labs.lab1.stockprice;

import java.util.Random;

public class StockFetcher {

    public static int getPrice(String ticker) {
        return new Random().nextInt(2000);
    }
}
