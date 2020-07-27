package com.arunav.learning.rxjava.labs.lab1.stockprice;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Subscriber {

    public static void main(String[] args) {
        List<String> tickers = Arrays.asList("AMZN", "GOOG", "AZN", "INTC", "TSLA", "AAPL", "MSFT");

        new Producer()
                .fetchPrices(tickers)
                .subscribe(System.out::println);

        System.out.println("Enter to quit");
        new Scanner(System.in).nextLine();
    }
}
